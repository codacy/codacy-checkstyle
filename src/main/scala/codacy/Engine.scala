package codacy

import com.codacy.tools.scala.seed.DockerEngine
import codacy.checkstyle.Checkstyle

object Engine extends DockerEngine(Checkstyle)()
