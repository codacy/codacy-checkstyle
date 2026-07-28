package codacy

import com.puppycrawl.tools.checkstyle.Checker
import scala.util.Using.Releasable

package object checkstyle {
  implicit val checkerReleasable: Releasable[Checker] = new Releasable[Checker] {
    def release(resource: Checker): Unit = resource.destroy()
  }
}
