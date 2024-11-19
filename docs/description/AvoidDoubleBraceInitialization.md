<div>

Detects double brace initialization.

</div>

Rationale: Double brace initialization (set of [Instance
Initializers](https://docs.oracle.com/javase/specs/jls/se12/html/jls-8.html#jls-8.6)
in class body) may look cool, but it is considered as anti-pattern and
should be avoided. This is also can lead to a hard-to-detect memory
leak, if the anonymous class instance is returned outside and other
object(s) hold reference to it. Created anonymous class is not static,
it holds an implicit reference to the outer class instance. See this
[blog
post](https://blog.jooq.org/dont-be-clever-the-double-curly-braces-anti-pattern/)
and [article](https://www.baeldung.com/java-double-brace-initialization)
for more details. Check ignores any comments and semicolons in class
body.
