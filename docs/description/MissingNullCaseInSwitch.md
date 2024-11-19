<div>

Checks that a given switch statement or expression that use a reference
type in its selector expression has a `null` case label.

</div>

Rationale: switch statements and expressions in Java throw a
`NullPointerException` if the selector expression evaluates to `null`.
As of Java 21, it is now possible to integrate a null check within the
switch, eliminating the risk of `NullPointerException` and simplifies
the code as there is no need for an external null check before entering
the switch.

See the [Java Language
Specification](https://docs.oracle.com/javase/specs/jls/se22/html/jls-15.html#jls-15.28)
for more information about switch statements and expressions.

Specifically, this check validates switch statement or expression that
use patterns or strings in their case labels.

Due to Checkstyle not being type-aware, this check cannot validate other
reference types, such as enums; syntactically, these are no different
from other constants.

**Attention**: this Check should be activated only on source code that
is compiled by jdk21 or above.
