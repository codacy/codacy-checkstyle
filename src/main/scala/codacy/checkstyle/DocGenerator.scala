package codacy.checkstyle

import java.io.IOException
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.{FileVisitResult, Files, Path, SimpleFileVisitor}

import com.codacy.plugins.api.results.{Parameter, Pattern, Result, Tool}
import play.api.libs.json.{JsObject, JsString, Json}

import scala.collection.immutable.ListSet
import scala.util.Try
import scala.xml._
import scala.xml.transform.{RewriteRule, RuleTransformer}
import scala.sys.process._

object DocGenerator {

  private case class PatternExtendedDescription(patternId: Pattern.Id, extendedDescription: String)

  def main(args: Array[String]): Unit = {
    val version: String = args.headOption
      .orElse {
        ResourceHelper
          .getResourceContent("docs/patterns.json")
          .toOption
          .flatMap { lines =>
            Json.parse(lines.mkString("\n")).as[JsObject].\("version").asOpt[String]
          }
      }
      .getOrElse {
        throw new Exception("No version provided")
      }

    withRepository(version) { directory =>
      val genPatterns = for {
        xml <- XML.loadFile(s"$directory/src/xdocs/checks.xml").to(List)
        tr <- xml \\ "tr"
        firstTd <- tr
          .map(_ \ "td")
          .flatMap(_.headOption) // we only need the first one (not the links inside descriptions)
        a <- (firstTd \ "a").to(List)
        href <- a.attribute("href").flatMap(_.headOption.map(_.text)).to(List)
        categoryFilename = href.takeWhile(_ != '.') if !href.startsWith("https://") && !href.startsWith("http://")
        categoryXml <- XML.loadFile(s"$directory/src/xdocs/$categoryFilename.xml").to(List)
        section <- categoryXml
          .\\("section")
          .to(List)
          .filterNot(e => Set("Content", "Overview").contains(e.attr("name")))
      } yield {
        val extendedDescription =
          section.\\("subsection").to(List).collectFirst {
            case ss if ss.attr("name") == "Description" =>
              val xmlString =
                checkstyleLinks
                  .transform(stripNamespaces(ss))
                  .map(_.buildString(stripComments = true))
                  .headOption
                  .getOrElse("")
                  .replaceAllLiterally("<source>", "<pre><code>")
                  .replaceAllLiterally("</source>", "</code></pre>")

              toMarkdown(xmlString).trim
          }

        val parameters = section.\\("subsection").to(List).collectFirst {
          case ss if ss.attr("name") == "Properties" =>
            ss.\\("tr")
              .to(List)
              .drop(1)
              .map(_.\\("td").to(List))
              .collect {
                case name :: description :: tpe :: default :: _ =>
                  val defaultValue = Option({
                    // Remove spaces and breaklines in default values
                    val defVal = default.text.replaceAll("""\n\s+""", "").trim.stripSuffix(".")
                    // Remove quotes around regular expressions
                    if (tpe.text.trim == "Regular Expression" && defVal != "null") {
                      // Leaves only what's inside outer quotes.
                      val f: Char => Boolean = _ != '"'
                      defVal.dropWhile(f).reverse.dropWhile(f).reverse.stripPrefix("\"").stripSuffix("\"")
                    } else {
                      defVal
                    }
                  }) // Filter out null values
                    .filterNot(_.equalsIgnoreCase("null"))
                    .getOrElse("")

                  // Try to parse numbers and booleans
                  val jsDefaultValue = {
                    val res = Try(Json.parse(defaultValue)).getOrElse(JsString(defaultValue))
                    res match {
                      case JsString("all files") => JsString("")
                      case o: JsObject if o.values.isEmpty => JsString("")
                      case any => any
                    }
                  }
                  val descriptionText =
                    if (description.text.length > 250) {
                      name.text
                    } else {
                      description.text
                    }

                  (
                    Parameter.Specification(Parameter.Name(name.text), Parameter.Value(jsDefaultValue)),
                    Parameter.Description(
                      Parameter.Name(name.text),
                      Parameter
                        .DescriptionText(descriptionText.trim.split(System.lineSeparator).map(_.trim).mkString(" "))
                    )
                  )
              }
        }

        val parametersSet = parameters
          .map(_.unzip)
          .map { case (specs, descs) => (specs.to(Set), descs.to(Set)) }

        val patternId = Pattern.Id(section.attr("name"))

        (Pattern.Specification(patternId, Result.Level.Info, Pattern.Category.CodeStyle, None, parametersSet.map {
          case (specs, _) => specs
        }, None), Pattern.Description(patternId, Pattern.Title(patternId.value), None, None, parametersSet.map {
          case (_, descs) => descs
        }), extendedDescription.map(PatternExtendedDescription(patternId, _)))
      }

      val (patternSpecifications, patternDescriptions, descriptions) = genPatterns.unzip3

      val sortedPatternSpecifications = ListSet(patternSpecifications.sortBy(_.patternId.value)(Ordering[String]): _*)
        .map(p => p.copy(parameters = p.parameters.map(pp => ListSet(pp.toSeq.sortBy(_.name.value): _*))))
      val sortedPatternDescriptions = ListSet(patternDescriptions.sortBy(_.patternId.value)(Ordering[String]): _*)
        .map(p => p.copy(parameters = p.parameters.map(pp => ListSet(pp.toSeq.sortBy(_.name.value): _*))))

      val spec = Tool.Specification(Tool.Name("checkstyle"), Some(Tool.Version(version)), sortedPatternSpecifications)
      val jsonSpecifications = Json.prettyPrint(Json.toJson(spec))
      val jsonDescriptions = Json.prettyPrint(Json.toJson(sortedPatternDescriptions))

      val repoRoot = new java.io.File(".")
      val docsRoot = new java.io.File(repoRoot, "src/main/resources/docs")
      val patternsFile = new java.io.File(docsRoot, "patterns.json")
      val descriptionsRoot = new java.io.File(docsRoot, "description")
      val descriptionsFile = new java.io.File(descriptionsRoot, "description.json")

      ResourceHelper.writeFile(patternsFile.toPath, jsonSpecifications + System.lineSeparator)
      ResourceHelper.writeFile(descriptionsFile.toPath, jsonDescriptions + System.lineSeparator)
      descriptions.collect {
        case Some(extendedDescription) if extendedDescription.extendedDescription.trim.nonEmpty =>
          val mdFile = new java.io.File(descriptionsRoot, s"${extendedDescription.patternId}.md")
          ResourceHelper.writeFile(mdFile.toPath, extendedDescription.extendedDescription + System.lineSeparator)
      }
    }
  }

  def stripNamespaces(node: Node): Node = {
    node match {
      case e: Elem =>
        e.copy(scope = TopScope, child = e.child.map(stripNamespaces))
      case _ =>
        node
    }
  }

  private implicit class NodeOps(e: Node) {

    def attr(k: String): String = {
      e.attribute(k).flatMap(_.headOption.map(_.text)).getOrElse("")
    }
  }

  private def withRepository[T](version: String)(block: Path => T): T = {
    val directory = Files.createTempDirectory("checkstyle")
    try {
      s"git clone git://github.com/checkstyle/checkstyle --depth 1 -b checkstyle-$version $directory".!!
      val res = block(directory)
      res
    } finally {
      Files.walkFileTree(directory, deleteRecursivelyVisitor)
    }
  }

  private val deleteRecursivelyVisitor = new SimpleFileVisitor[Path] {
    override def visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult = {
      Files.delete(file)
      FileVisitResult.CONTINUE
    }

    override def postVisitDirectory(dir: Path, exc: IOException): FileVisitResult = {
      Files.delete(dir)
      FileVisitResult.CONTINUE
    }
  }

  private def toMarkdown(html: String): String = {
    val directory = Files.createTempDirectory("checkstyleDoc")
    try {
      val file = Files.createTempFile(directory, "checkstyle-doc", ".xml")
      Files.write(file, html.getBytes())
      Seq("pandoc", "-f", "html", "-t", "commonmark", file.toString).!!
    } finally {
      Files.walkFileTree(directory, deleteRecursivelyVisitor)
    }
  }

  /**
    * Xml RuleTranformer to add checkstyle page base on
    * internal website links
    */
  private val checkstyleLinks = new RuleTransformer(new RewriteRule {
    override def transform(node: Node): Node = {
      node match {
        case elem: Elem =>
          val href = elem \@ "href"
          if (href.nonEmpty && !href.startsWith("http://") && !href
              .startsWith("https://") && (href.split("/").length <= 1)) {
            if (!href.contains(".html")) {
              <span>{elem.child}</span>
            } else {
              val newLink = s"https://checkstyle.org/$href"
              <a href={newLink}>{elem.child}</a>
            }
          } else elem
        case n => n
      }
    }
  })
}
