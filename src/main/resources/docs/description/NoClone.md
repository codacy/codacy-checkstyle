Since Checkstyle 5.0

Checks that the clone method is not overridden from the Object class.

Rationale: The clone method relies on strange, hard to follow rules that are difficult to get right and do not work in all situations. In some cases, either a copy constructor or a static factory method can be used instead of the clone method to return copies of an object. For more information on rules for the clone method and its issues, see Effective Java: Programming Language Guide First Edition by Joshua Bloch pages 45-52.

This check is almost exactly the same as the \{@link NoFinalizerCheck\}