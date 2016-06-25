# Description

According to
[Code Conventions for the Java Programming Language](http://www.oracle.com/technetwork/java/javase/documentation/codeconventions-141855.html#1852),
the parts of a class or interface declaration should appear in the following
order:

1. Class (static) variables. First the public class variables, then protected,
   then package level (no access modifier), and then private.
1. Instance variables. First the public class variables, then protected, then
   package level (no access modifier), and then private.
1. Constructors
1. Methods

**ATTENTION**: the check skips class fields which have
[forward references](http://docs.oracle.com/javase/specs/jls/se8/html/jls-8.html#jls-8.3.3)
from validation due to the fact that we have Checkstyle's limitations to clearly
detect user intention of fields location and grouping. For example,

```
public class A {
    private double x = 1.0;
    private double y = 2.0;
    public double slope = x / y; // will be skipped from validation due to forward reference
}
```

# Examples

```
class K {
    int a;
    void m(){}
    K(){}  // "Constructor definition in wrong order"
    int b; // "Instance variable definition in wrong order"
}
```
