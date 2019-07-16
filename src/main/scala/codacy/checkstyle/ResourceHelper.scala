package codacy.checkstyle

import java.io.InputStream
import java.net.URL
import java.nio.charset.{CodingErrorAction, StandardCharsets}
import java.nio.file.{Files, Path, StandardOpenOption}

import scala.io.{Codec, Source}
import scala.util.{Failure, Properties, Try}

object ResourceHelper {

  implicit val codec: Codec = Codec.UTF8
  codec.onMalformedInput(CodingErrorAction.REPLACE)
  codec.onUnmappableCharacter(CodingErrorAction.REPLACE)

  def getResourceContent(path: String): Try[List[String]] = {
    Option(getClass.getClassLoader.getResource(path))
      .map { url =>
        getResourceContent(url)
      }
      .getOrElse {
        Failure(new Exception("The path provided was not found"))
      }
  }

  private def getResourceContent(url: URL): Try[List[String]] = {
    getResourceStream(url).flatMap { stream =>
      val lines = Try {
        Source
          .fromInputStream(stream)
          .mkString
          .split(Properties.lineSeparator)
          .toList
      }

      Try(stream.close())

      lines
    }
  }

  private def getResourceStream(url: URL): Try[InputStream] = {
    Some(url)
      .map { file =>
        Try(file.openStream())
      }
      .getOrElse {
        Failure(new Exception("The URL provided is not valid"))
      }
  }

  def writeFile(path: Path, content: String): Try[Path] = {
    Try(
      Files.write(
        path,
        content.getBytes(StandardCharsets.UTF_8),
        StandardOpenOption.CREATE,
        StandardOpenOption.TRUNCATE_EXISTING,
        StandardOpenOption.WRITE
      )
    )
  }

}
