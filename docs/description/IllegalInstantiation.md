Checks for illegal instantiations where a factory method is preferred.

Rationale: Depending on the project, for some classes it might be
preferable to create instances through factory methods rather than
calling the constructor.

A simple example is the `java.lang.Boolean` class. For performance
reasons, it is preferable to use the predefined constants ` TRUE` and
`FALSE`. Constructor invocations should be replaced by calls to
`Boolean.valueOf()`.

Some extremely performance sensitive projects may require the use of
factory methods for other classes as well, to enforce the usage of
number caches or object pools.
