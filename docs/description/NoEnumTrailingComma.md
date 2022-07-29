Checks that enum definition does not contain a trailing comma.
Rationale: JLS allows trailing commas in arrays and enumerations, but
does not allow them in other locations. To unify the coding style, the
use of trailing commas should be prohibited.

    enum Foo1 {
      FOO,
      BAR;
    }
            

The check demands that there should not be any comma after last constant
in enum definition.

    enum Foo1 {
      FOO,
      BAR, //violation
    }
