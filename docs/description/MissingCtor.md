<div>

Checks that classes (except abstract ones) define a constructor and
don't rely on the default one.

</div>

Compatibility note: **when creating** an explicit constructor already in
existing class that used by other in codebases that you do not own, it
must match precisely the declaration of the automatically generated
constructor; even if the constructor should logically be protected, it
must be made public to match the declaration of the automatically
generated constructor, for **compatibility**.

See [Documentation Comments Style
Guide](https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html#defaultconstructors).
