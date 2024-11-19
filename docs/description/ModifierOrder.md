<div>

Checks that the order of modifiers conforms to the suggestions in the
[Java Language specification, § 8.1.1, 8.3.1,
8.4.3](https://docs.oracle.com/javase/specs/jls/se16/preview/specs/sealed-classes-jls.html)
and
[9.4](https://docs.oracle.com/javase/specs/jls/se11/html/jls-9.html).
The correct order is:

</div>

1.  `public`
2.  `protected`
3.  `private`
4.  `abstract`
5.  `default`
6.  `static`
7.  `sealed`
8.  `non-sealed`
9.  `final`
10. `transient`
11. `volatile`
12. `synchronized`
13. `native`
14. `strictfp`

In additional, modifiers are checked to ensure all annotations are
declared before all other modifiers.

Rationale: Code is easier to read if everybody follows a standard.

ATTENTION: We skip [type
annotations](https://www.oracle.com/technical-resources/articles/java/ma14-architect-annotations.html)
from validation.
