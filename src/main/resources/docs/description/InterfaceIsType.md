Since Checkstyle 3.1

Implements Joshua Bloch, Effective Java, Item 17 - Use Interfaces only to define types.

According to Bloch, an interface should describe a *type*. It is therefore inappropriate to define an interface that does not contain any methods but only constants. The Standard interface [ javax.swing.SwingConstants][javax.swing.SwingConstants] is an example of an interface that would be flagged by this check.

The check can be configured to also disallow marker interfaces like `java.io.Serializable`, that do not contain methods or constants at all.


[javax.swing.SwingConstants]: https://docs.oracle.com/javase/8/docs/api/javax/swing/SwingConstants.html