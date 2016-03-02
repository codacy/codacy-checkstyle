package codacy.checkstyle

import java.nio.file.Path

import codacy.dockerApi._
import codacy.dockerApi.utils.{CommandRunner, FileHelper, ToolHelper}
import play.api.libs.json.{JsString, JsValue}

import scala.util.Try
import scala.xml.{XML, Elem}

object Checkstyle extends Tool {

  override def apply(path: Path, conf: Option[List[PatternDef]], files: Option[Set[Path]])(implicit spec: Spec): Try[List[Result]] = {
    Try {
      val fullConfig = ToolHelper.getPatternsToLint(conf)
      val filesToLint: List[String] = files.fold(List(path.toString)) {
        paths =>
          paths.map(_.toString).toList
      }
      val configFile = generateConfig(fullConfig)
      val resultFile = FileHelper.createTmpFile("", "result", ".xml")

      val command = Seq("java", "-jar", "checkstyle.jar", "-c", configFile.toString, "-f", "xml",
        "-o", resultFile.toAbsolutePath.toString) ++ filesToLint

      CommandRunner.exec(command.toList) match {
        case Right(resultFromTool) =>
          //If it throws we want to crash, because the checkstyle always return a valid XML
          val resultFromToolXml = XML.loadFile(resultFile.toFile)
          parseToolResult(resultFromToolXml, resultFromTool.stderr, filesToLint)
        case Left(failure) =>
          throw failure
      }
    }
  }

  private def generateConfig(conf: Option[List[PatternDef]]): Path = {
    lazy val defaultConfig =
      <module name="Checker">
        <module name="TreeWalker">
        </module>
      </module>

    val doctype =
      """<?xml version="1.0"?>
    <!DOCTYPE module PUBLIC
    "-//Puppy Crawl//DTD Check Configuration 1.3//EN"
    "http://www.puppycrawl.com/dtds/configuration_1_3.dtd" >"""

    val xmlConfig = conf.fold(defaultConfig) {
      allPatterns =>
        val (globalPatterns, patterns) = allPatterns.partition(isGlobalPattern)

        <module name="Checker">
          {globalPatterns.map(generatePatternConfig)}
          <module name="TreeWalker">
            {patterns.map(generatePatternConfig)}
          </module>
        </module>
    }
    FileHelper.createTmpFile(doctype + xmlConfig.toString)
  }

  private def generatePatternConfig(pattern: PatternDef): Elem = {
    lazy val parameterlessPattern = <module name={pattern.patternId.value}/>

    pattern.parameters.fold(parameterlessPattern) {
      case parameters if parameters.isEmpty =>
        parameterlessPattern
      case parameters =>
        <module name={pattern.patternId.value}>
          {parameters.map(generateParameterConfig)}
        </module>
    }
  }

  private def generateParameterConfig(parameter: ParameterDef): Elem = {
      <property name={parameter.name.value} value={jsValueToString(parameter.value)}/>
  }

  private def parseToolResult(outputXml: Elem, errLines: List[String], filesAnalysed: List[String]): List[Result] = {
    val results = if (errLines.nonEmpty) {
      errLines.flatMap {
        case _ =>
          //Ceckstyle crashes all the tool when we have a problem in just a single file
          filesAnalysed.map(filename => FileError(SourcePath(filename), None))
      }
    } else {
      (outputXml \ "file").flatMap {
        file =>
          val filename = file \@ "name"

          (file \ "error").map {
            error =>
              val line = (error \@ "line").toInt
              val message = error \@ "message"
              val patternId = (error \@ "source").split("\\.").last.stripSuffix("Check")
              Issue(SourcePath(filename), ResultMessage(message), PatternId(patternId), ResultLine(line))
          }
      }
    }
    results.toList
  }

  private def jsValueToString(value: JsValue) = {
    value match {
      case JsString(v) => v
      case v => v.toString
    }
  }

  private lazy val globalPatterns: Set[String] = Set(
    "SeverityMatchFilter",
    "SuppressionCommentFilter",
    "SuppressionFilter",
    "SuppressWithNearbyCommentFilter",
    "Header",
    "RegexpHeader",
    "JavadocPackage",
    "Translation",
    "RegexpMultiline",
    "RegexpSingleline",
    "RegexpOnFilename",
    "FileLength",
    "FileTabCharacter",
    "NewlineAtEndOfFile",
    "UniqueProperties"
  )

  private def isGlobalPattern(pattern: PatternDef) = {
    globalPatterns.contains(pattern.patternId.value)
  }
}
