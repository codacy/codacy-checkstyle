Checks that package names conform to a specified pattern.

The default value of `format` for module `PackageName` has been chosen
to match the requirements in the [Java Language
specification](https://docs.oracle.com/javase/specs/jls/se8/html/jls-6.html#jls-6.5.3)
and the Sun coding conventions. However both underscores and uppercase
letters are rather uncommon, so most configurations should probably
assign value `^[a-z]+(\.[a-z][a-z0-9]*)*$` to `format` for module
`PackageName`.
