package codacy.checkstyle

import java.io.IOException
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.{FileVisitResult, Files, Path, SimpleFileVisitor}

import better.files._
import com.codacy.plugins.api.results.{Parameter, Pattern, Result, Tool}
import play.api.libs.json.{JsNumber, JsObject, JsString, Json}

import scala.collection.immutable.ListSet
import scala.util.Try
import scala.xml._
import scala.xml.transform.{RewriteRule, RuleTransformer}
import scala.sys.process._

object DocGenerator {

  val defaultPatterns = List(
    "UnusedImports",
    "FileTabCharacter",
    "LineLength",
    "AvoidStarImport",
    "OneTopLevelClass",
    "NoLineWrap",
    "EmptyBlock",
    "EmptyCatchBlock",
    "FileTabCharacter",
    "CommentsIndentation",
    "EmptyLineSeparator"
  )

  private case class PatternExtendedDescription(patternId: Pattern.Id, extendedDescription: String)

  def main(args: Array[String]): Unit = {
    withRepository { directory =>
      val xdocs = directory / "src" / "xdocs"
      val genPatterns = for {
        xml <- XML.loadFile((xdocs / "checks.xml").toJava).to(List)
        tr <- xml \\ "tr"
        firstTd <- tr
          .map(_ \ "td")
          .flatMap(_.headOption) // we only need the first one (not the links inside descriptions)
        a <- (firstTd \ "a").to(List)
        href <- a.attribute("href").flatMap(_.headOption.map(_.text)).to(List)
        categoryFilename = href.takeWhile(_ != '.') if !href.startsWith("https://") && !href.startsWith("http://")
        categoryXml <- XML.loadFile((xdocs / s"$categoryFilename.xml").toJava).to(List)
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
                // charset parameter has a default value that comes from another pattern
                // and can only be supported with a configuration file.
                case name :: description :: tpe :: default :: _ if name.text != "charset" =>
                  val defaultValue = Option({
                    // Remove spaces and breaklines in default values
                    val defVal = default.text.replaceAll("""\n\s+""", "").trim.stripSuffix(".")
                    // Remove quotes around regular expressions
                    if (tpe.text.trim == "Pattern" && defVal != "null") {
                      // Leaves only what's inside outer quotes.
                      val f: Char => Boolean = _ != '"'
                      defVal.dropWhile(f).reverse.dropWhile(f).reverse.stripPrefix("\"").stripSuffix("\"")
                    } else {
                      defVal
                    }
                  }) // Filter out null values
                    .filterNot(value => value.equalsIgnoreCase("null") || value.equalsIgnoreCase("empty"))
                    .orNull

                  // Try to parse numbers and booleans
                  val jsDefaultValue = {
                    val res = Try(Json.parse(defaultValue)).getOrElse(JsString(defaultValue))
                    res match {
                      case JsString("all files") => JsString("")
                      case JsString("java.lang.Integer.MAX_VALUE") => JsNumber(Integer.MAX_VALUE)
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

        val (parametersSpecs, parametersDesc) = parameters
          .map(_.unzip)
          .map { case (specs, descs) => (specs, descs) }
          .getOrElse((List(), List()))

        val patternId = Pattern.Id(section.attr("name"))
        val enabledByDefault = defaultPatterns.contains(patternId.value)

        (
          Pattern.Specification(
            patternId,
            Result.Level.Info,
            Pattern.Category.CodeStyle,
            None,
            parametersSpecs.toSet,
            Set.empty,
            enabled = enabledByDefault
          ),
          Pattern.Description(patternId, Pattern.Title(patternId.value), None, None, parametersDesc.toSet),
          extendedDescription.map(PatternExtendedDescription(patternId, _))
        )
      }

      val (patternSpecifications, patternDescriptions, descriptions) = genPatterns.unzip3

      val sortedPatternSpecifications = ListSet(patternSpecifications.sortBy(_.patternId.value)(Ordering[String]): _*)
        .map(p => p.copy(parameters = ListSet(p.parameters.toSeq.sortBy(_.name.value): _*)))
      val sortedPatternDescriptions = ListSet(patternDescriptions.sortBy(_.patternId.value)(Ordering[String]): _*)
        .map(p => p.copy(parameters = ListSet(p.parameters.toSeq.sortBy(_.name.value): _*)))

      val spec = Tool.Specification(
        Tool.Name("checkstyle"),
        Some(Tool.Version(Versions.checkstyleVersion)),
        sortedPatternSpecifications
      )
      val jsonSpecifications = Json.prettyPrint(Json.toJson(spec))
      val jsonDescriptions = Json.prettyPrint(Json.toJson(sortedPatternDescriptions))

      val docsRoot = file"docs"
      val descriptionsRoot = docsRoot / "description"

      (docsRoot / "patterns.json").writeText(jsonSpecifications + System.lineSeparator)
      (descriptionsRoot / "description.json").writeText(jsonDescriptions + System.lineSeparator)
      descriptions.collect {
        case Some(extendedDescription) if extendedDescription.extendedDescription.trim.nonEmpty =>
          val mdFile = descriptionsRoot / s"${extendedDescription.patternId}.md"
          mdFile.writeText(extendedDescription.extendedDescription + System.lineSeparator)
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

  private def withRepository[T](block: File => T): T = {
    File.temporaryDirectory("checkstyle") { directory =>
      s"git clone https://github.com/checkstyle/checkstyle -c advice.detachedHead=false --depth 1 -b checkstyle-${Versions.checkstyleVersion} $directory".!!
      block(directory)
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
