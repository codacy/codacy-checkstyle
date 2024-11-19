<div>

Measures the number of distinct classes that are instantiated within the
given class or record. This type of coupling is not caused by
inheritance or the object-oriented paradigm. Generally speaking, any
data type with other data types as members or local variable that is an
instantiation (object) of another class has data abstraction coupling
(DAC). The higher the DAC, the more complex the structure of the class.

</div>

This check processes files in the following way:

1.  Iterates over the list of tokens (defined below) and counts all
    mentioned classes.
    - [PACKAGE_DEF](../../apidocs/com/puppycrawl/tools/checkstyle/api/TokenTypes.html#IMPORT)
    - [IMPORT](../../apidocs/com/puppycrawl/tools/checkstyle/api/TokenTypes.html#IMPORT)
    - [CLASS_DEF](../../apidocs/com/puppycrawl/tools/checkstyle/api/TokenTypes.html#CLASS_DEF)
    - [INTERFACE_DEF](../../apidocs/com/puppycrawl/tools/checkstyle/api/TokenTypes.html#INTERFACE_DEF)
    - [ENUM_DEF](../../apidocs/com/puppycrawl/tools/checkstyle/api/TokenTypes.html#ENUM_DEF)
    - [LITERAL_NEW](../../apidocs/com/puppycrawl/tools/checkstyle/api/TokenTypes.html#LITERAL_NEW)
    - [RECORD_DEF](../../apidocs/com/puppycrawl/tools/checkstyle/api/TokenTypes.html#RECORD_DEF)
2.  If a class was imported with direct import (i.e.
    `import java.math.BigDecimal`), or the class was referenced with the
    package name (i.e. `java.math.BigDecimal value`) and the package was
    added to the `excludedPackages` parameter, the class does not
    increase complexity.
3.  If a class name was added to the `excludedClasses` parameter, the
    class does not increase complexity.
