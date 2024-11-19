<div>

Checks the number of other types a given
class/record/interface/enum/annotation relies on. Also, the square of
this has been shown to indicate the amount of maintenance required in
functional programs (on a file basis) at least.

</div>

This check processes files in the following way:

1.  Iterates over all tokens that might contain type reference.
2.  If a class was imported with direct import (i.e.
    `import java.math.BigDecimal`), or the class was referenced with the
    package name (i.e. `java.math.BigDecimal value`) and the package was
    added to the `excludedPackages` parameter, the class does not
    increase complexity.
3.  If a class name was added to the `excludedClasses` parameter, the
    class does not increase complexity.
