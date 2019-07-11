package codacy.checkstyle

import org.scalatest.AsyncFunSuite
import DocGenerator._

class DocGeneratorTests extends AsyncFunSuite {
  test("Keep only what's inside the doublequotes.") {
    val s = "\"$^\" (empty)"
    val s1 = """"^$|^\s+$\""""

    assert(s.dropWhileLeftRight(_ != '"') === "\"$^\"")
    assert(s1.dropWhileLeftRight(_ != '"') === s1)
  }
}
