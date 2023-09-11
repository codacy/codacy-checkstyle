import com.typesafe.sbt.packager.docker.Cmd
import sjsonnew._
import sjsonnew.BasicJsonProtocol._
import sjsonnew.support.scalajson.unsafe._

name := "codacy-checkstyle"

scalaVersion := "2.13.12"

lazy val checkstyleVersion = "10.12.3"

Compile / sourceGenerators += Def.task {
  val file = (Compile / sourceManaged).value / "codacy" / "checkstyle" / "Versions.scala"
  IO.write(file, s"""package codacy.checkstyle
                    |object Versions {
                    |  val checkstyleVersion: String = "$checkstyleVersion"
                    |}
                    |""".stripMargin)
  Seq(file)
}.taskValue

Compile / mainClass := Some("codacy.Engine")

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-xml" % "1.2.0",
  "com.codacy" %% "codacy-engine-scala-seed" % "5.0.3",
  "com.puppycrawl.tools" % "checkstyle" % checkstyleVersion
)

Universal / javaOptions ++= Seq("-XX:MinRAMPercentage=60.0", "-XX:MaxRAMPercentage=90.0")

enablePlugins(JavaAppPackaging)
