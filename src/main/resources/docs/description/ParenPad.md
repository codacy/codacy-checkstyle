Checks the policy on the padding of parentheses; i.e. whether a
space is required after a left parenthesis and before a right
parenthesis, or such spaces are forbidden. No check occurs at
the right parenthesis after an empty for iterator, at the left
parenthesis before an empty for initialization, or at the right
parenthesis of a try-with-resources resource specification where
the last resource variable has a trailing semi-colon.
Use Check [EmptyForIteratorPad](https://checkstyle.org/config_whitespace.html#EmptyForIteratorPad) to validate empty for iterators and
[EmptyForInitializerPad](https://checkstyle.org/config_whitespace.html#EmptyForInitializerPad) to validate empty for initializers.
Typecasts are also not checked, as there is
[TypecastParenPad](https://checkstyle.org/config_whitespace.html#TypecastParenPad) to validate them.
