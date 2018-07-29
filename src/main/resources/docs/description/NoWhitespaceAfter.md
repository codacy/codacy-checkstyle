Since Checkstyle 3.0

Checks that there is no whitespace after a token. More specifically, it checks that it is not followed by whitespace, or (if linebreaks are allowed) all characters on the line after are whitespace. To forbid linebreaks after a token, set property `allowLineBreaks` to `false`.

The check processes  ARRAY\_DECLARATOR and INDEX\_OP tokens specially from other tokens. Actually it is checked that there is no whitespace before this tokens, not after them. Space after the  ANNOTATIONS before  ARRAY\_DECLARATOR and  INDEX\_OP will be ignored.