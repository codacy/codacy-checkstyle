package codacy.checkstyle

import java.nio.file.{Files, Path}

import better.files.File
import com.codacy.plugins.api.results.{Parameter, Pattern, Result, Tool}
import com.overzealous.remark.Options.FencedCodeBlocks
import com.overzealous.remark.{Options, Remark}
import org.jsoup.Jsoup
import play.api.libs.json.{JsObject, JsString, Json}

import scala.collection.immutable.ListSet
import scala.sys.process._
import scala.util.Try
import scala.xml._

object DocGenerator {

  private case class PatternExtendedDescription(patternId: Pattern.Id, extendedDescription: String)

  def main(args: Array[String]): Unit = {
    val version: String = args.headOption.orElse {
      ResourceHelper.getResourceContent("docs/patterns.json").toOption
        .flatMap { lines => Json.parse(lines.mkString("\n")).as[JsObject].\("version").asOpt[String] }
    }.getOrElse {
      throw new Exception("No version provided")
    }

    withRepository(version) { directory =>
      val genPatterns = for {
        xml <- XML.loadFile(s"$directory/src/xdocs/checks.xml").to[List]
        a <- xml.\\("a").to[List]
        href <- a.attribute("href").flatMap(_.headOption.map(_.text)).to[List]
        categoryFilename = href.takeWhile(_ != '.') if !href.startsWith("https://") && !href.startsWith("http://")
        categoryXml <- XML.loadFile(s"$directory/src/xdocs/$categoryFilename.xml").to[List]
        section <- categoryXml.\\("section").to[List].filterNot(e => Set("Content", "Overview").contains(e.attr("name")))
      } yield {
        val extendedDescription = section.\\("subsection").to[List].collectFirst {
          case ss if ss.attr("name") == "Description" =>
            val xmlString =
              stripNamespaces(ss)
                .buildString(stripComments = true)
                .replaceAllLiterally("<source>", "<pre><code>")
                .replaceAllLiterally("</source>", "</code></pre>")

            val document = Jsoup.parseBodyFragment(xmlString)

            val opts = Options.github()
            opts.fencedCodeBlocks = FencedCodeBlocks.DISABLED
            opts.fencedCodeBlocksWidth = 3
            new Remark(opts).convert(document)
        }

        val parameters = section.\\("subsection").to[List].collectFirst {
          case ss if ss.attr("name") == "Properties" =>
            ss.\\("tr").to[List]
              .drop(1)
              .map(_.\\("td").to[List])
              .collect {
                case name :: description :: _ :: default :: _ =>
                  val defaultValue = Option({
                    // Remove spaces and breaklines in default values
                    val defVal = default.text.replaceAll("""\n\s+""", "").trim.stripSuffix(".")
                    // Remove quotes around default values
                    if (defVal.startsWith("\"") && defVal.endsWith("\"")) {
                      defVal.stripPrefix("\"").stripSuffix("\"")
                    } else {
                      defVal
                    }
                  }) // Filter out null values
                    .filterNot(_.equalsIgnoreCase("null")).getOrElse("")

                  // Try to parse numbers and booleans
                  val jsDefaultValue = Try(Json.parse(defaultValue)).getOrElse(JsString(defaultValue))

                  val descriptionText =
                    if(description.text.length > 250) {
                      name.text
                    } else {
                      description.text
                    }

                  (Parameter.Specification(Parameter.Name(name.text), Parameter.Value(jsDefaultValue)),
                    Parameter.Description(Parameter.Name(name.text), Parameter.DescriptionText(descriptionText)))
              }
        }

        val parametersSet = parameters
          .map(_.unzip)
          .map { case (specs, descs) => (specs.to[Set], descs.to[Set]) }

        val patternId = Pattern.Id(section.attr("name"))

        (Pattern.Specification(patternId, Result.Level.Info, Pattern.Category.CodeStyle, parametersSet.map { case (specs, _) => specs }, None),
          Pattern.Description(patternId, Pattern.Title(patternId.value), None, None, parametersSet.map { case (_, descs) => descs }),
          extendedDescription.map(PatternExtendedDescription(patternId, _)))
      }

      val (patternSpecifications, patternDescriptions, descriptions) = genPatterns.unzip3

      val sortedPatternSpecifications = ListSet(patternSpecifications.sortBy(_.patternId.value)(Ordering[String]): _*)
        .map(p => p.copy(parameters = p.parameters.map(pp => ListSet(pp.toSeq.sortBy(_.name.value): _*))))
      val sortedPatternDescriptions = ListSet(patternDescriptions.sortBy(_.patternId.value)(Ordering[String]): _*)
        .map(p => p.copy(parameters = p.parameters.map(pp => ListSet(pp.toSeq.sortBy(_.name.value): _*))))

      val spec = Tool.Specification(Tool.Name("Checkstyle"), Some(Tool.Version(version)), sortedPatternSpecifications)
      val jsonSpecifications = Json.prettyPrint(Json.toJson(spec))
      val jsonDescriptions = Json.prettyPrint(Json.toJson(sortedPatternDescriptions))

      val repoRoot = new java.io.File(".")
      val docsRoot = new java.io.File(repoRoot, "src/main/resources/docs")
      val patternsFile = new java.io.File(docsRoot, "patterns.json")
      val descriptionsRoot = new java.io.File(docsRoot, "description")
      val descriptionsFile = new java.io.File(descriptionsRoot, "description.json")

      ResourceHelper.writeFile(patternsFile.toPath, jsonSpecifications)
      ResourceHelper.writeFile(descriptionsFile.toPath, jsonDescriptions)
      descriptions.collect { case Some(extendedDescription) if extendedDescription.extendedDescription.trim.nonEmpty =>
        val descriptionsFile = new java.io.File(descriptionsRoot, s"${extendedDescription.patternId}.md")
        ResourceHelper.writeFile(descriptionsFile.toPath, extendedDescription.extendedDescription)
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
    s"git clone git://github.com/checkstyle/checkstyle --depth 1 -b checkstyle-$version $directory".!!
    val res = block(directory)
    File(directory).delete(true)
    res
  }

}
