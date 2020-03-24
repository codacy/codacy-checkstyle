Checks for empty line separators after header, package, all import
declarations, fields, constructors, methods, nested classes, static
initializers and instance initializers.

ATTENTION: empty line separator is required between token siblings, not
after line where token is found. If token does not have same type
sibling then empty line is required at its end (for example for
CLASS\_DEF it is after '}'). Also, trailing comments are skipped.
