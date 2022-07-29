Evaluates Xpath query and report violation on all matching AST nodes.
This check allows user to implement custom checks using Xpath. If Xpath
query is not specified explicitly, then the check does nothing.

It is recommended to define custom message for violation to explain what
is not allowed and what to use instead, default message might be too
abstract. To customize a message you need to add `message` element with
**matchxpath.match** as `key` attribute and desired message as `value`
attribute.

Please read more about Xpath syntax at [Xpath
Syntax](https://www.saxonica.com/html/documentation10/expressions/).
Information regarding Xpath functions can be found at [XSLT/XPath
Reference](https://www.saxonica.com/html/documentation10/functions/fn/).
Note, that **@text** attribute can be used only with token types that
are listed in
[XpathUtil](https://github.com/checkstyle/checkstyle/search?q=%22TOKEN_TYPES_WITH_TEXT_ATTRIBUTE+%3D+Arrays.asList%22).
