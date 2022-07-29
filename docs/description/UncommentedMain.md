Detects uncommented `main` methods.

Rationale: A `main` method is often used for debugging purposes. When
debugging is finished, developers often forget to remove the method,
which changes the API and increases the size of the resulting class or
JAR file. Except for the real program entry points, all `main` methods
should be removed or commented out of the sources.
