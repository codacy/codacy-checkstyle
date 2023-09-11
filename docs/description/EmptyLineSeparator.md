Checks for empty line separators before package, all import
declarations, fields, constructors, methods, nested classes, static
initializers and instance initializers.

Checks for empty line separators before not only statements but
implementation and documentation comments and blocks as well.

ATTENTION: empty line separator is required between token siblings, not
after line where token is found. If token does not have a sibling of the
same type, then empty line is required at its end (for example for
CLASS_DEF it is after '}'). Also, trailing comments are skipped.
