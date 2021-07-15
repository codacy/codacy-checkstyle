Checks if the javadoc has [leading
asterisks](https://docs.oracle.com/en/java/javase/14/docs/specs/javadoc/doc-comment-spec.html#leading-asterisks)
on each line.

The check does not require asterisks on the first line, nor on the last
line if it is blank. All other lines in a Javadoc should start with `*`,
including blank lines and code blocks.
