Checks identifiers with a pattern for a set of illegal names, such as
those that are restricted or contextual keywords. Examples include
"yield", "record", "\_", and "var". Please read more at [Java Language
Specification](https://docs.oracle.com/javase/specs/jls/se14/html/jls-3.html#jls-3.9)
to get to know more about restricted keywords. Since this check uses a
pattern to specify valid identifiers, users can also prohibit the usage
of certain symbols, such as "$", or any non-ascii character.
