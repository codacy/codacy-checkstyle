<div>

Checks that Javadoc block tags for field declarations do not contain
tags that are semantically invalid for fields. Specifically, `@author`,
`@version`, `@param`, `@return`, `@throws`/`@exception`, `@uses`, and
`@provides` tags are meaningless on field declarations.

</div>

Field declarations do not have parameters, return types, or throw
declarations, and module/type-level tags like `@author`, `@version`,
`@uses`, and `@provides` are inappropriate on field declarations.
Therefore, such Javadoc block tags are considered inappropriate and
should be removed.
