Restrict the number of `&&`, `||`, `&`, `|` and `^` in an expression.

Rationale: Too many conditions leads to code that is difficult to read
and hence debug and maintain.

Note that the operators `&` and `|` are not only integer bitwise
operators, they are also the [non-shortcut
versions](https://docs.oracle.com/javase/specs/jls/se11/html/jls-15.html#jls-15.22.2)
of the boolean operators `&&` and `||`.

Note that `&`, `|` and `^` are not checked if they are part of
constructor or method call because they can be applied to non boolean
variables and Checkstyle does not know types of methods from different
classes.