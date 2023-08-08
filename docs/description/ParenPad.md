Checks the policy on the padding of parentheses; that is whether a space
is required after a left parenthesis and before a right parenthesis, or
such spaces are forbidden. No check occurs at the right parenthesis
after an empty for iterator, at the left parenthesis before an empty for
initialization, or at the right parenthesis of a try-with-resources
resource specification where the last resource variable has a trailing
semicolon. Use Check
[EmptyForIteratorPad](https://checkstyle.org/emptyforiteratorpad.html#EmptyForIteratorPad)
to validate empty for iterators and
[EmptyForInitializerPad](https://checkstyle.org/emptyforinitializerpad.html#EmptyForInitializerPad)
to validate empty for initializers. Typecasts are also not checked, as
there is
[TypecastParenPad](https://checkstyle.org/typecastparenpad.html#TypecastParenPad)
to validate them.
