Checks that sealed classes and interfaces have a permits list.

Rationale: When a permits clause is omitted from a sealed class, any
class within the same compilation unit can extend it. This differs from
other sealed classes where permitted subclasses are explicitly declared,
making them readily visible to the reader. Without a permits clause,
identifying potential subclasses requires searching the entire
compilation unit, which can be challenging, especially in large files
with complex class hierarchies.

See the [Java Language
Specification](https://docs.oracle.com/javase/specs/jls/se22/html/jls-13.html#jls-13.4.2)
for more information about sealed classes.
