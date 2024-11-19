<div>

Checks that the parts of a class, record, or interface declaration
appear in the order suggested by the [Code Conventions for the Java
Programming
Language](../../styleguides/sun-code-conventions-19990420/CodeConventions.doc2.html#a1852).

</div>

According to [Code Conventions for the Java Programming
Language](../../styleguides/sun-code-conventions-19990420/CodeConventions.doc2.html#a1852)
, the parts of a class or interface declaration should appear in the
following order:

1.  Class (static) variables. First the public class variables, then
    protected, then package level (no access modifier), and then
    private.
2.  Instance variables. First the public class variables, then
    protected, then package level (no access modifier), and then
    private.
3.  Constructors
4.  Methods

Purpose of **ignore\*** option is to ignore related violations, however
it still impacts on other class members.

ATTENTION: the check skips class fields which have [forward
references](https://docs.oracle.com/javase/specs/jls/se11/html/jls-8.html#jls-8.3.3)
from validation due to the fact that we have Checkstyle's limitations to
clearly detect user intention of fields location and grouping. For
example:

    public class A {
      private double x = 1.0;
      private double y = 2.0;
      public double slope = x / y; // will be skipped from validation due to forward reference
    }
