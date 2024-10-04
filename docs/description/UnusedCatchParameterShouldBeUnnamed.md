Ensures that catch parameters that are not used are declared as an
unnamed variable.

Rationale:

- Improves code readability by clearly indicating which parameters are
  unused.
- Follows Java conventions for denoting unused parameters with an
  underscore (`_`).

See the [Java Language
Specification](https://docs.oracle.com/en/java/javase/21/docs/specs/unnamed-jls.html)
for more information about unnamed variables.

**Attention**: This check should be activated only on source code that
is compiled by jdk21 or higher; unnamed catch parameters came out as the
first preview in Java 21.
