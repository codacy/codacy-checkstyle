Since Checkstyle 3.0

Checks that the order of modifiers conforms to the suggestions in the [Java Language specification, sections 8.1.1, 8.3.1, 8.4.3][Java Language specification_ sections 8.1.1_ 8.3.1_ 8.4.3] and [ 9.4][9.4]. The correct order is:

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

ATTENTION: We skip [ type annotations][type annotations] from validation.


[Java Language specification_ sections 8.1.1_ 8.3.1_ 8.4.3]: https://docs.oracle.com/javase/specs/jls/se8/html/jls-8.html
[9.4]: https://docs.oracle.com/javase/specs/jls/se8/html/jls-9.html
[type annotations]: http://www.oracle.com/technetwork/articles/java/ma14-architect-annotations-2177655.html