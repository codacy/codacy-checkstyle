package codacy.checkstyle

import java.nio.file.Path

import codacy.dockerApi._

import scala.util.Try

object Checkstyle extends Tool {

  override def apply(path: Path, conf: Option[List[PatternDef]], files: Option[Set[Path]])(implicit spec: Spec): Try[List[Result]] = {
    Try(List.empty[Result])
  }

 
}
