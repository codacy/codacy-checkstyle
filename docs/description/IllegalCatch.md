<div>

Checks that certain exception types do not appear in a `catch`
statement.

</div>

Rationale: catching `java.lang.Exception`, `java.lang.Error` or
`java.lang.RuntimeException` is almost never acceptable. Novice
developers often simply catch Exception in an attempt to handle multiple
exception classes. This unfortunately leads to code that inadvertently
catches `NullPointerException`, `OutOfMemoryError`, etc.
