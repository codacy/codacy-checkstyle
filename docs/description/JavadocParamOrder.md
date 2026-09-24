<div>

Checks that `@param` tags in Javadoc comments are in the same order as
the parameters in the declaration.

</div>

Type parameters must come before regular parameters. For record
declarations, record components are treated as regular parameters and
must be documented after type parameters. For compact constructors, the
expected parameter order is the order of the record components in the
record declaration.

The check does not validate missing, extra, or duplicate `@param` tags.
It reports only tags that move backward in the declaration order.
