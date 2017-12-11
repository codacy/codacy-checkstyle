Since Checkstyle 3.0

Checks that there is no whitespace before a token. More specifically, it checks that it is not preceded with whitespace, or (if linebreaks are allowed) all characters on the line before are whitespace. To allow linebreaks before a token, set property `allowLineBreaks` to `true`. No check occurs before semi-colons in empty for loop initializers or conditions.