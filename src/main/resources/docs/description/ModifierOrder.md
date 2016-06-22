Checks that the order of modifiers conforms to the suggestions in the [Java Language specification, sections 8.1.1, 8.3.1 and 8.4.3](http://docs.oracle.com/javase/specs/jls/se8/html/jls-8.html). The correct order is:

1. public
2. protected
3. private
4. abstract
5. static
6. final
7. transient
8. volatile
9. synchronized
10. native
11. strictfp

ATTENTION: We skip [type annotations](https://blogs.oracle.com/java-platform-group/entry/java_8_s_new_type) from validation.

Source: http://checkstyle.sourceforge.net/config_modifier.html#ModifierOrder
