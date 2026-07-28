<div>

Checks that enum definition does not contain a trailing comma.
Rationale: JLS allows trailing commas in arrays and enumerations, but
does not allow them in other locations. To unify the coding style, the
use of trailing commas should be prohibited.

</div>

<div class="wrapper">

``` prettyprint
enum Foo1 {
  FOO,
  BAR;
}
        
```

</div>

The check demands that there should not be any comma after last constant
in enum definition.

<div class="wrapper">

``` prettyprint
enum Foo1 {
  FOO,
  BAR, // violation
}
        
```

</div>
