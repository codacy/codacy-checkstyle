package codacy.checkstyle

import java.nio.file.Path

import better.files.File
import codacy.docker.api
import codacy.docker.api._
import codacy.docker.api.utils.ToolHelper
import codacy.dockerApi.utils.FileHelper
import com.puppycrawl.tools.checkstyle._
import com.puppycrawl.tools.checkstyle.api.{AuditListener, Configuration}
import play.api.libs.json.{JsString, JsValue}

import scala.collection.JavaConversions._
import scala.util.Try
import scala.xml.Elem

object Checkstyle extends Tool {

  override def apply(source: Source.Directory, configuration: Option[List[Pattern.Definition]], files: Option[Set[Source.File]],
                     options: Map[api.Configuration.Key, api.Configuration.Value])
                    (implicit specification: Tool.Specification): Try[List[Result]] = Try {
    val fullConfig = ToolHelper.patternsToLint(configuration)
    val filesToLint: List[String] = files.fold(List(source.path.toString)) {
      paths =>
        paths.map(_.toString).toList
    }

    val configFile = generateConfig(source.path.toString, fullConfig)
      .map(_.toAbsolutePath.toString)
      .getOrElse {
        throw new Exception("Could not generate nor find configuration")
      }

    val listener = new CodacyListener()
    val config = ConfigurationLoader.loadConfiguration(
      configFile,
      new PropertiesExpander(System.getProperties),
      ConfigurationLoader.IgnoredModulesOptions.EXECUTE,
      ThreadModeSettings.SINGLE_THREAD_MODE_INSTANCE
    )

    runCheckstyle(filesToLint, config, listener)

    (listener.issues ++ listener.failures).to[List]
  }

  private def runCheckstyle(files: List[String], config: Configuration, listener: AuditListener): Int = {
    val checker = new Checker()
    try {
      checker.setModuleClassLoader(classOf[Checker].getClassLoader)
      checker.configure(config)
      checker.addListener(listener)
      checker.process(files.map(f => File(f).toJava))
    } finally {
      checker.destroy()
    }
  }

  private lazy val nativeConfigFileNames = Set("checkstyle.xml")
  //TODO: the right thing to do here would be to read the default xml @ compile time and extract the doctype!
  private val doctype =
    """<?xml version="1.0"?>
    <!DOCTYPE module PUBLIC
    "-//Puppy Crawl//DTD Check Configuration 1.3//EN"
    "http://www.puppycrawl.com/dtds/configuration_1_3.dtd" >"""


  private def generateConfig(projectRoot: String, conf: Option[List[Pattern.Definition]]): Option[Path] = {
    lazy val nativeConfig =
      nativeConfigFileNames.flatMap { nativeConfigFileName =>
        File(projectRoot)
          .listRecursively
          .filter(f => f.name == nativeConfigFileName)
          .map(_.path)
      }
        .to[List]
        .sortBy(_.toString.length)
        .headOption

    lazy val codacyConfig = conf.map { allPatterns =>
      val (globalPatterns, patterns) = allPatterns.partition(isGlobalPattern)

      val xmlConfig =
        <module name="Checker">
          {globalPatterns.map(generatePatternConfig)}<module name="TreeWalker">
          {patterns.map(generatePatternConfig)}
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

  private def isGlobalPattern(pattern: Pattern.Definition) = {
    globalPatterns.contains(pattern.patternId.value)
  }

}
