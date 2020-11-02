Checks that the order of modifiers conforms to the suggestions in the
[Java Language specification, § 8.1.1, 8.3.1,
8.4.3](https://docs.oracle.com/javase/specs/jls/se11/html/jls-8.html)
and
[9.4](https://docs.oracle.com/javase/specs/jls/se11/html/jls-9.html).
The correct order is:

1.  `public`
2.  `protected`
3.  `private`
4.  `abstract`
5.  `default`
6.  `static`
7.  `final`
8.  `transient`
9.  `volatile`
10. `synchronized`
11. `native`
12. `strictfp`

In additional, modifiers are checked to ensure all annotations are
declared before all other modifiers.

Rationale: Code is easier to read if everybody follows a standard.

ATTENTION: We skip [type
annotations](https://www.oracle.com/technical-resources/articles/java/ma14-architect-annotations.html)
from validation.
