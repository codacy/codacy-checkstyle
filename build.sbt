import com.typesafe.sbt.packager.docker.Cmd
import com.amazonaws.services.s3.model.Region
import sjsonnew.shaded.scalajson.ast.unsafe._
import sjsonnew.support.scalajson.unsafe._

name := "codacy-checkstyle"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.12.8"

mainClass in Compile := Some("codacy.Engine")

resolvers := Seq("Sonatype OSS Snapshots".at("https://oss.sonatype.org/content/repositories/releases")) ++
  resolvers.value

lazy val toolVersionKey = settingKey[String]("The version of the underlying tool retrieved from patterns.json")

toolVersionKey := {
  val jsonFile = (resourceDirectory in Compile).value / "docs" / "patterns.json"
  val json = Parser.parseFromFile(jsonFile).get.asInstanceOf[JObject]
  json.value.find(_.field == "version").get.value.asInstanceOf[JString].value
}

resolvers ++= Seq(
  "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/",
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/releases",
  Resolver.jcenterRepo
)

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-xml" % "1.2.0" withSources(),
  "com.codacy" %% "codacy-engine-scala-seed" % "3.0.296",
  "com.puppycrawl.tools" % "checkstyle" % toolVersionKey.value,
  "com.overzealous" % "remark" % "1.1.0",
  "org.scalatest" %% "scalatest" % "3.0.8" % Test
)

enablePlugins(AshScriptPlugin)

enablePlugins(DockerPlugin)

version in Docker := "1.0.0"

mappings in Universal ++= {
  (resourceDirectory in Compile) map { resourceDir: File =>
    val src = resourceDir / "docs"
    val dest = "/docs"

    for {
      path <- src.allPaths.get if !path.isDirectory
    } yield path -> path.toString.replaceFirst(src.toString, dest)
  }
}.value

val dockerUser = "docker"
val dockerGroup = "docker"

daemonUser in Docker := dockerUser

daemonGroup in Docker := dockerGroup

dockerBaseImage := "openjdk:8-jre-alpine"

dockerEntrypoint := Seq(s"/opt/docker/bin/${name.value}")

dockerCommands := dockerCommands.value.flatMap {
  case cmd @ Cmd("WORKDIR", _) => Seq(
    Cmd("WORKDIR", "/src")
  )
  case cmd @ Cmd("ADD", _) => Seq(
    Cmd("RUN", s"adduser -u 2004 -D $dockerUser"),
    cmd,
    Cmd("RUN", "mv /opt/docker/docs /docs")
  )
  case other => List(other)
}

s3region := Region.EU_Ireland
