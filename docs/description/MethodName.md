<div>

Checks that method names conform to a specified pattern.

</div>

Also, checks if a method name has the same name as the residing class.
The default is false (it is not allowed). It is legal in Java to have
method with the same name as a class. As long as a return type is
specified it is a method and not a constructor which it could be easily
confused as. Does not check-style the name of an overridden methods
because the developer does not have a choice in renaming such methods.
