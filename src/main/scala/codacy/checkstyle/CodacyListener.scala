package codacy.checkstyle

import codacy.docker.api.{ErrorMessage, Pattern, Result, Source}
import com.puppycrawl.tools.checkstyle.api.{AuditEvent, AuditListener}

import scala.collection.mutable

class CodacyListener extends AuditListener {

  val issues: mutable.ListBuffer[Result.Issue] = mutable.ListBuffer()
  val failures: mutable.ListBuffer[Result.FileError] = mutable.ListBuffer()

  override def addError(event: AuditEvent): Unit = {
    issues += Result.Issue(
      Source.File(event.getFileName),
      Result.Message(event.getMessage),
      Pattern.Id(event.getSourceName.split("\\.").last.stripSuffix("Check")),
      Source.Line(event.getLine)
    )
  }

  override def addException(event: AuditEvent, throwable: Throwable): Unit = {
    failures += Result.FileError(
      Source.File(event.getFileName),
      Some(ErrorMessage(event.getMessage))
    )
  }

  override def auditStarted(event: AuditEvent): Unit = ()

  override def fileFinished(event: AuditEvent): Unit = ()

  override def auditFinished(event: AuditEvent): Unit = ()

  override def fileStarted(event: AuditEvent): Unit = ()

}
