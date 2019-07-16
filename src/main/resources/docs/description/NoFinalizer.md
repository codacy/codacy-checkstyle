Verifies there are no `finalize()` methods defined in a class.

See [ Object.finalize()][Object.finalize]

Rationale: Finalizers are unpredictable, often dangerous, and generally unnecessary. Their use can cause erratic behavior, poor performance, and portability problems. For more information for the finalize method and its issues, see Effective Java: Programming Language Guide Third Edition by Joshua Bloch, §8.


[Object.finalize]: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Object.html#finalize%28%29