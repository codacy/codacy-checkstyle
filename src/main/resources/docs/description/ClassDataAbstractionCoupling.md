Since Checkstyle 3.4

This metric measures the number of instantiations of other classes within the given class. This type of coupling is not caused by inheritance or the object oriented paradigm. Generally speaking, any data type with other data types as members or local variable that is an instantiation (object) of another class has data abstraction coupling (DAC). The higher the DAC, the more complex the structure of the class.

This check processes files in the following way:

1.  Iterates over the list of tokens (defined below) and counts all mentioned classes.
    
     *  PACKAGE\_DEF
     *  IMPORT
     *  CLASS\_DEF
     *  INTERFACE\_DEF
     *  ENUM\_DEF
     *  LITERAL\_NEW
2.  If a class was imported with direct import (i.e. `import java.math.BigDecimal`), or the class was referenced with the package name (i.e. `java.math.BigDecimal value`) and the package was added to the `excludedPackages` parameter, the class does not increase complexity.
3.  If a class name was added to the `excludedClasses` parameter, the class does not increase complexity.