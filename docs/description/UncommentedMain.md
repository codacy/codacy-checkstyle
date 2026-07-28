<div>

Detects uncommented `main` methods.

</div>

Rationale: A `main` method is often used for debugging purposes. When
debugging is finished, developers often forget to remove the method,
which changes the API and increases the size of the resulting class or
JAR file. Except for the real program entry points, all `main` methods
should be removed or commented out of the sources.

Note: Compact source files ([JEP 512](https://openjdk.org/jeps/512)) are
skipped by design. The whole purpose of compact source files is to serve
as standalone single-file programs with a `main` method as the entry
point. Unlike regular classes where leftover `main` methods may be
debugging artifacts, compact sources inherently require a `main` method
to function.
