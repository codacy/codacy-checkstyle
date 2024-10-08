Checks that switch statement has a `default` clause.

Rationale: It's usually a good idea to introduce a default case in every
switch statement. Even if the developer is sure that all currently
possible cases are covered, this should be expressed in the default
branch, e.g. by using an assertion. This way the code is protected
against later changes, e.g. introduction of new types in an enumeration
type.

This check does not validate any switch expressions. Rationale: The
compiler requires switch expressions to be exhaustive. This means that
all possible inputs must be covered.

This check does not validate switch statements that use pattern or null
labels. Rationale: Switch statements that use pattern or null labels are
checked by the compiler for exhaustiveness. This means that all possible
inputs must be covered.

See the [Java Language
Specification](https://docs.oracle.com/javase/specs/jls/se22/html/jls-15.html#jls-15.28)
for more information about switch statements and expressions.

See the [Java Language
Specification](https://docs.oracle.com/javase/specs/jls/se22/html/jls-14.html#jls-14.30)
for more information about patterns.
