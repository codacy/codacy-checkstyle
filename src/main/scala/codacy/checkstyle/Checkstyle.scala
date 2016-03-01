package codacy.checkstyle

import java.nio.file.Path

import codacy.dockerApi._
import codacy.dockerApi.utils.{CommandRunner, FileHelper, ToolHelper}
import play.api.libs.json.Json

import scala.util.Try
import scala.xml.Elem

object Checkstyle extends Tool {

  override def apply(path: Path, conf: Option[List[PatternDef]], files: Option[Set[Path]])(implicit spec: Spec): Try[List[Result]] = {
    Try {
      val fullConfig = ToolHelper.getPatternsToLint(conf)
      val filesToLint: List[String] = files.fold(List(path.toString)) {
        paths =>
          paths.map(_.toString).toList
      }
      val configFile = generateConfig(fullConfig)

      val command = Seq("java", "-jar", "checkstyle.jar", "-c", configFile.toString) ++ filesToLint

      CommandRunner.exec(command.toList) match {
        case Right(resultFromTool) =>
          parseToolResult(resultFromTool.stdout, resultFromTool.stderr, path)
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
      patterns =>
        <module name="Checker">
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
      <property name={parameter.name.value} value={Json.stringify(parameter.value)}/>
  }

  def parseToolResult(lines: List[String], errLines: List[String], path: Path): List[Result] = {

    val RegMatch = """\[ERROR\]\s*(.+?):([0-9]+):[0-9]*:?\s*(.+)\s*\[(.+)\]""".r
    val FileErrorMatch = """([^C]\S+):([0-9]+:[0-9]+:.*)""".r

    val issues = lines.collect {
      case RegMatch(filePath, line, message, patternId) =>
        Issue(SourcePath(filePath), ResultMessage(message), PatternId(patternId), ResultLine(line.toInt))
    }

    val errors = errLines.collect {
      case FileErrorMatch(filename, message) =>
        FileError(SourcePath(filename), Some(ErrorMessage(message)))
    }
    issues ++ errors
  }
}
