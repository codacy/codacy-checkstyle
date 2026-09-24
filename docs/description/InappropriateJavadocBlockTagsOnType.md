<div>

Checks that Javadoc block tags for type definitions (class, interface,
enum, record, annotation) do not contain tags that are semantically
invalid for types. Specifically, `@return` and `@throws`/`@exception`
tags are meaningless on type declarations since types neither return
values nor throw exceptions.

</div>

Type declarations (classes, interfaces, enums, records, annotations) do
not have return types or throw declarations. Therefore, `@return` and
`@throws`/`@exception` Javadoc block tags used in their Javadoc comments
are considered inappropriate and should be removed or replaced with
proper documentation.
