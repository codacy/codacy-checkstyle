package codacy

import codacy.dockerApi.DockerEngine
import codacy.checkstyle.Checkstyle

object Engine extends DockerEngine(Checkstyle)
