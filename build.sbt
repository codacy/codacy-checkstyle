import com.typesafe.sbt.packager.docker.{Cmd, ExecCmd}
import scala.util.parsing.json.JSON
import scala.io.Source

name := """codacy-checkstyle"""

version := "1.0.0-SNAPSHOT"

val languageVersion = "2.11.8"

scalaVersion := languageVersion

mainClass in Compile := Some("codacy.Engine")

lazy val toolVersionKey = SettingKey[String]("The version of the underlying tool retrieved from patterns.json")

toolVersionKey := {
  val jsonFile = (resourceDirectory in Compile).value / "docs" / "patterns.json"
  val toolMap = JSON.parseFull(Source.fromFile(jsonFile).getLines().mkString)
    .getOrElse(throw new Exception("patterns.json is not a valid json"))
    .asInstanceOf[Map[String, String]]
  toolMap.getOrElse[String]("version", throw new Exception("Failed to retrieve 'version' from patterns.json"))
}
resolvers ++= Seq(
  "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/",
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/releases",
  Resolver.jcenterRepo
)

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.4.8" withSources(),
  "org.scala-lang.modules" %% "scala-xml" % "1.0.4" withSources(),
  "com.codacy" %% "codacy-engine-scala-seed" % "2.7.9",
  "com.puppycrawl.tools" % "checkstyle" % toolVersionKey.value,
  "com.overzealous" % "remark" % "1.1.0"
)

enablePlugins(JavaAppPackaging)

enablePlugins(DockerPlugin)

version in Docker := "1.0.0"

lazy val installAll = TaskKey[String]("Retrieve the install commands")

installAll := {
  s"""apk --no-cache add bash
     |&& rm -rf /var/cache/apk/*""".stripMargin.replaceAll(System.lineSeparator(), " ")
}

mappings in Universal <++= (resourceDirectory in Compile) map { (resourceDir: File) =>
  val src = resourceDir / "docs"
  val dest = "/docs"

  for {
    path <- (src ***).get
    if !path.isDirectory
  } yield path -> path.toString.replaceFirst(src.toString, dest)
}

val dockerUser = "docker"
val dockerGroup = "docker"

daemonUser in Docker := dockerUser

daemonGroup in Docker := dockerGroup

dockerBaseImage := "develar/java"

dockerEntrypoint := Seq(s"/opt/docker/bin/${name.value}")

dockerCommands := dockerCommands.value.flatMap {
  case cmd@Cmd("WORKDIR", _) => List(
    Cmd("WORKDIR", "/src"),
    Cmd("RUN", installAll.value),
    Cmd("RUN", s"adduser -u 2004 -D $dockerUser")
  )
  case cmd@(Cmd("ADD", _)) => List(
    cmd,
    Cmd("RUN", "mv /opt/docker/docs /docs"),
    ExecCmd("RUN", Seq("chown", "-R", s"$dockerUser:$dockerGroup", "/docs"): _*),
    ExecCmd("RUN", Seq("chown", "-R", s"$dockerUser:$dockerGroup", "/opt"): _*)
  )
  case other => List(other)
}
