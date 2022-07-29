Checks that there is no whitespace after a token. More specifically, it
checks that it is not followed by whitespace, or (if linebreaks are
allowed) all characters on the line after are whitespace. To forbid
linebreaks after a token, set property `allowLineBreaks` to
`           false`.

The check processes
[ARRAY_DECLARATOR](apidocs/com/puppycrawl/tools/checkstyle/api/TokenTypes.html#ARRAY_DECLARATOR)
and
[INDEX_OP](apidocs/com/puppycrawl/tools/checkstyle/api/TokenTypes.html#INDEX_OP)
tokens specially from other tokens. Actually it is checked that there is
no whitespace before these tokens, not after them. Space after the
[ANNOTATIONS](apidocs/com/puppycrawl/tools/checkstyle/api/TokenTypes.html#ANNOTATIONS)
before
[ARRAY_DECLARATOR](apidocs/com/puppycrawl/tools/checkstyle/api/TokenTypes.html#ARRAY_DECLARATOR)
and
[INDEX_OP](apidocs/com/puppycrawl/tools/checkstyle/api/TokenTypes.html#INDEX_OP)
will be ignored.

If the annotation is between the type and the array, the check will skip
validation for spaces

    public void foo(final char @NotNull [] param) {} // No violation
