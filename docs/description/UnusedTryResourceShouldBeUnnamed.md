<div>

Ensures that try-with-resources resource variables that are not used are
declared as an unnamed variable.

</div>

Rationale:

- Improves code readability by clearly indicating which resources are
  unused.
- Follows Java conventions for denoting unused variables with an
  underscore (`_`).

Only declared resources inside the try-with-resources parentheses are
checked (i.e. `var a = lock()` or `AutoCloseable a = lock()`). Resources
that are referenced but not declared inside the try (e.g.
`try (releaser) { }`) are never flagged, because those resources cannot
be replaced with `_`.

See the [Java Language
Specification](https://docs.oracle.com/en/java/javase/21/docs/specs/unnamed-jls.html)
for more information about unnamed variables.

**Attention**: This check should be activated only on source code that
is compiled by jdk21 or higher; unnamed variables came out as a preview
feature in Java 21 and became a standard part of the language in Java
22.
