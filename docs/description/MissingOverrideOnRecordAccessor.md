<div>

Verifies that explicitly declared record accessor methods include the
`@Override` annotation.

</div>

Per [JEP 395](https://openjdk.org/jeps/395), the meaning of the
`@Override` annotation was extended to include explicitly declared
accessor methods for record components.

This check focuses only on record component accessor methods. It does
not attempt to detect general method overrides from interfaces or
superclasses, due to Checkstyle's single-file analysis limitations.
