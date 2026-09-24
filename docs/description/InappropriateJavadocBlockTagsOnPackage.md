<div>

Checks that Javadoc block tags for package definitions do not contain
tags that are semantically invalid for packages.

</div>

Package definitions do not have return types or throw declarations.
Therefore, `@return` and `@throws`/`@exception` Javadoc block tags used
in their Javadoc comments are considered inappropriate and should be
removed or replaced with proper documentation.

Parent is `com.puppycrawl.tools.checkstyle.TreeWalker`

Violation Message Keys:

- `javadoc.inappropriate.tag`
