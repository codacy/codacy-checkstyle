<div>

Checks that references to instance variables and methods of the present
object are explicitly of the form "this.varName" or
"this.methodName(args)" and that those references don't rely on the
default behavior when "this." is absent.

</div>

Warning: the Check is very controversial if 'validateOnlyOverlapping'
option is set to 'false' and not that actual nowadays.

Rationale:

1.  The same notation/habit for C++ and Java (C++ have global methods,
    so having "this." do make sense in it to distinguish call of method
    of class instead of global).
2.  Non-IDE development (ease of refactoring, some clearness to
    distinguish static and non-static methods).
