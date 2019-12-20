package codacy.checkstyle

import java.nio.file.{Path, Paths}

import better.files.File
import com.codacy.plugins.api.results.{Parameter, Pattern, Result, Tool}
import com.codacy.plugins.api.{Options, Source}
import com.codacy.tools.scala.seed.utils.FileHelper
import com.codacy.tools.scala.seed.utils.ToolHelper._
import com.puppycrawl.tools.checkstyle._
import com.puppycrawl.tools.checkstyle.api.Configuration
import play.api.libs.json.{JsString, JsValue}
import scala.jdk.CollectionConverters._
import scala.util.Try
import scala.xml.Elem
import scala.util.Failure
import scala.util.Success
import com.codacy.plugins.api.ErrorMessage

object Checkstyle extends Tool {

  def apply(
      source: Source.Directory,
      configuration: Option[List[Pattern.Definition]],
      files: Option[Set[Source.File]],
      options: Map[Options.Key, Options.Value]
  )(implicit specification: Tool.Specification): Try[List[Result]] = Try {
    val fullConfig = configuration.withDefaultParameters

    val filesToLint: Set[Source.File] = files.getOrElse(Set(Source.File(source.path)))

    val configFile = {
      generateConfig(source, fullConfig)
        .map(_.toAbsolutePath.toString)
        .getOrElse {
          throw new Exception("Could not generate nor find configuration")
        }
    }

    val config = ConfigurationLoader.loadConfiguration(
      configFile,
      new PropertiesExpander(System.getProperties),
      ConfigurationLoader.IgnoredModulesOptions.EXECUTE,
      ThreadModeSettings.SINGLE_THREAD_MODE_INSTANCE
    )
    run(filesToLint, config)
  }

  private def run(files: Set[Source.File], config: Configuration): List[Result] = {
    val res = for {
      file <- files.view
      listener = new CodacyListener()
      checker = new Checker()
      _ = checker.setModuleClassLoader(classOf[Checker].getClassLoader)
      _ = checker.configure(config)
      _ = checker.addListener(listener)
      result = Try(checker.process(List(File(file.path).toJava).asJava)) match {
        case Success(_) => None
        case Failure(e) => Some(Result.FileError(file, Some(ErrorMessage(e.getMessage))))
      }
      _ = checker.destroy()
    } yield listener.issues ++ listener.failures ++ result
    res.flatten.to(List)
  }

  private lazy val nativeConfigFileNames = Set("checkstyle.xml")
  //TODO: the right thing to do here would be to read the default xml @ compile time and extract the doctype!
  private val doctype =
    """<?xml version="1.0"?>
    <!DOCTYPE module PUBLIC
    "-//Puppy Crawl//DTD Check Configuration 1.3//EN"
    "http://www.puppycrawl.com/dtds/configuration_1_3.dtd" >"""

  private def generateConfig(root: Source.Directory, conf: Option[List[Pattern.Definition]]): Option[Path] = {
    lazy val nativeConfig =
      FileHelper.findConfigurationFile(Paths.get(root.path), nativeConfigFileNames)

    lazy val codacyConfig = conf.map { allPatterns =>
      val (globalPatterns, localPatterns) = allPatterns.partition(isGlobalPattern)

      val xmlConfig =
        <module name="Checker">
          {globalPatterns.map(generatePatternConfig)}
          <module name="TreeWalker">
            {localPatterns.map(generatePatternConfig)}
          </module>
        </module>

      FileHelper.createTmpFile(doctype + xmlConfig.toString)
    }

    codacyConfig orElse nativeConfig
  }

  private def generatePatternConfig(pattern: Pattern.Definition): Elem = {
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

  private def generateParameterConfig(parameter: Parameter.Definition): Elem = {
    <property name={parameter.name.value} value={jsValueToString(parameter.value)}/>
  }

  private def jsValueToString(value: JsValue): String = {
    value match {
      case JsString(v) => v
      case v => v.toString
    }
  }

  private val globalPatterns: Set[String] = Set(
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
    "UniqueProperties",
    "LineLength"
  )

  private def isGlobalPattern(pattern: Pattern.Definition) = {
    globalPatterns.contains(pattern.patternId.value)
  }
}
