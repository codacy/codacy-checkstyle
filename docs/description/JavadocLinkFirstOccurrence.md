<div>

Checks that in Javadoc comments, each API name is linked with `{@link}`
or `{@linkplain}` only on its first occurrence. Subsequent links to the
same API name in the same comment are flagged.

</div>

Rationale: From the [Documentation Comments style
guide](https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html),
links call attention to themselves by their color and underline in HTML,
and by their length in source code doc comments. Linking the same name
multiple times is redundant.

Two links are considered to reference the same API name if they resolve
to the same canonical name. Simple names are resolved through explicit
imports, types declared in the current file, star imports and the
`java.lang` package. Names containing dots are resolved through imports
of their outermost segment; otherwise they are compared as written.
