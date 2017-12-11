Since Checkstyle 3.4

The number of other classes a given class relies on. Also the square of this has been shown to indicate the amount of maintenance required in functional programs (on a file basis) at least.

This check processes files in the following way:

1.  Iterates over the list of tokens (defined below) and counts all mentioned classes.
    
     *  PACKAGE\_DEF
     *  IMPORT
     *  CLASS\_DEF
     *  INTERFACE\_DEF
     *  ENUM\_DEF
     *  TYPE
     *  LITERAL\_NEW
     *   LITERAL\_THROWS 
     *   ANNOTATION\_DEF 
2.  If a class was imported with direct import (i.e. `import java.math.BigDecimal`), or the class was referenced with the package name (i.e. `java.math.BigDecimal value`) and the package was added to the `excludedPackages` parameter, the class does not increase complexity.
3.  If a class name was added to the `excludedClasses` parameter, the class does not increase complexity.