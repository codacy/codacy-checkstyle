Since Checkstyle 3.0

Check that parameters for methods, constructors, and catch blocks are final. Interface, abstract, and native methods are not checked: the final keyword does not make sense for interface, abstract, and native method parameters as there is no code that could modify the parameter.

Rationale: Changing the value of parameters during the execution of the method's algorithm can be confusing and should be avoided. A great way to let the Java compiler prevent this coding style is to declare parameters final.