Ensures that lambda parameters that are not used are declared as an
unnamed variable.

Rationale:

- Improves code readability by clearly indicating which parameters are
  unused.
- Follows Java conventions for denoting unused parameters with an
  underscore (`_`).

See the [Java Language
Specification](https://docs.oracle.com/en/java/javase/21/docs/specs/unnamed-jls.html)
for more information about unnamed variables.

**Attention**: Unnamed variables are available as a preview feature in
Java 21, and became an official part of the language in Java 22. This
check should be activated only on source code which meets those
requirements.
