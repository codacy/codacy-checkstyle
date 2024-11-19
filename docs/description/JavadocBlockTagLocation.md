<div>

Checks that a [javadoc block
tag](https://docs.oracle.com/en/java/javase/11/docs/specs/doc-comment-spec.html#block-tags)
appears only at the beginning of a line, ignoring leading asterisks and
white space. A block tag is a token that starts with `@` symbol and is
preceded by a whitespace. This check ignores block tags in comments and
inside inline tags {@code } and {@literal }.

</div>

Rationale: according to [the
specification](https://docs.oracle.com/en/java/javase/11/docs/specs/doc-comment-spec.html#block-tags)
all javadoc block tags should be placed at the beginning of a line. Tags
that are not placed at the beginning are treated as plain text. To
recognize intentional tag placement to text area it is better to escape
the `@` symbol, and all non-escaped tags should be located at the
beginning of the line. See NOTE section for details on how to escape.
