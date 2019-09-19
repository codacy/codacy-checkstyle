Restricts the number of return statements in methods, constructors and
lambda expressions (2 by default). Ignores specified methods (`equals()`
by default).

**max** property will only check returns in methods and lambdas that
return a specific value (Ex: \'return 1;\').

**maxForVoid** property will only check returns in methods,
constructors, and lambdas that have no return type (IE \'return;\'). It
will only count visible return statements. Return statements not
normally written, but implied, at the end of the method/constructor
definition will not be taken into account. To disallow \"return;\" in
void return type methods, use a value of 0.

Rationale: Too many return points can mean that code is attempting to do
too much or may be difficult to understand.