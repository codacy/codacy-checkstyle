<div>

Checks that Javadocs are located at the correct position. As specified
at [Documentation Comment Specification for the Standard
Doclet](https://docs.oracle.com/en/java/javase/11/docs/specs/doc-comment-spec.html),
Javadocs are recognized only when placed immediately before module,
package, class, interface, constructor, method, or field declarations.
Any other position, like in the body of a method, will be ignored by the
javadoc tool and is considered invalid by this check.

</div>
