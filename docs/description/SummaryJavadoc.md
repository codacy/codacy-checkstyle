Checks that [Javadoc summary
sentence](https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html#firstsentence)
does not contain phrases that are not recommended to use. Summaries that
contain only the `{@inheritDoc}` tag are skipped. Summaries that contain
a non-empty {@code {@return}} are allowed. Check also violate Javadoc
that does not contain first sentence, though with {@code {@return}} a
period is not required as the Javadoc tool adds it.
