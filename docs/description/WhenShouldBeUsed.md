Ensures that `when` is used instead of a single `if` statement inside a
case block.

Rationale: Java 21 has introduced enhancements for switch statements and
expressions that allow the use of patterns in case labels. The `when`
keyword is used to specify condition for a case label, also called as
guarded case labels. This syntax is more readable and concise than the
single `if` statement inside the pattern match block.

See the [Java Language
Specification](https://docs.oracle.com/javase/specs/jls/se22/html/jls-14.html#jls-Guard)
for more information about guarded case labels.

See the [Java Language
Specification](https://docs.oracle.com/javase/specs/jls/se22/html/jls-14.html#jls-14.30)
for more information about patterns.
