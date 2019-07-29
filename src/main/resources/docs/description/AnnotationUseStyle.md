This check controls the style with the usage of annotations.

Annotations have three element styles starting with the least verbose.

* `ElementStyle.COMPACT_NO_ARRAY`
* `ElementStyle.COMPACT`
* `ElementStyle.EXPANDED`

To not enforce an element style a `ElementStyle.IGNORE` type is provided.
The desired style can be set through the `elementStyle` property.

Using the `ElementStyle.EXPANDED` style is more verbose.
The expanded version is sometimes referred to as "named parameters" in other languages.

Using the `ElementStyle.COMPACT` style is less verbose.
This style can only be used when there is an element called 'value' which is either
the sole element or all other elements have default values.

Using the `ElementStyle.COMPACT_NO_ARRAY` style is less verbose.
It is similar to the `ElementStyle.COMPACT` style but single value arrays
are flagged.
With annotations a single value array does not need to be placed in an array initializer.

The ending parenthesis are optional when using annotations with no elements.
To always require ending parenthesis use the `ClosingParens.ALWAYS` type.
To never have ending parenthesis use the `ClosingParens.NEVER` type.
To not enforce a closing parenthesis preference a `ClosingParens.IGNORE`
type is provided. Set this through the `closingParens` property.

Annotations also allow you to specify arrays of elements in a standard format.
As with normal arrays, a trailing comma is optional.
To always require a trailing comma use the `TrailingArrayComma.ALWAYS` type.
To never have a trailing comma use the `TrailingArrayComma.NEVER` type.
To not enforce a trailing array comma preference a `TrailingArrayComma.IGNORE`
type is provided.
Set this through the `trailingArrayComma` property.

By default the `ElementStyle` is set to `COMPACT_NO_ARRAY`,
the `TrailingArrayComma` is set to `NEVER`,
and the `ClosingParens` is set to `NEVER`.

According to the JLS, it is legal to include a trailing comma
in arrays used in annotations but Sun's Java 5 \& 6 compilers will not
compile with this syntax. This may in be a bug in Sun's compilers
since eclipse 3.4's built-in compiler does allow this syntax as
defined in the JLS. Note: this was tested with compilers included with
JDK versions 1.5.0.17 and 1.6.0.11 and the compiler included with eclipse 3.4.1.

See [Java Language specification, §9.7](https://docs.oracle.com/javase/specs/jls/se11/html/jls-9.html#jls-9.7).
