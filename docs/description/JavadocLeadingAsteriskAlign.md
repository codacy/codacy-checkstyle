<div>

Checks the alignment of [leading
asterisks](https://docs.oracle.com/en/java/javase/14/docs/specs/javadoc/doc-comment-spec.html#leading-asterisks)
in a Javadoc comment. The Check ensures that leading asterisks are
aligned vertically under the first asterisk ( \* ) of opening Javadoc
tag. The alignment of closing Javadoc tag ( \*/ ) is also checked. If a
closing Javadoc tag contains non-whitespace character before it then
it's alignment will be ignored. If the ending javadoc line contains a
leading asterisk, then that leading asterisk's alignment will be
considered, the closing Javadoc tag will be ignored.

</div>

If you're using tabs then specify the the tab width in the
[tabWidth](../../config.html#tabWidth) property.
