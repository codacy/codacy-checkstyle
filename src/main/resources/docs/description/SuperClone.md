Since Checkstyle 3.2

Checks that an overriding `clone()` method invokes `super.clone()`. Does not check native methods, as they have no possible java defined implementation.

Reference: [Object.clone()][Object.clone].


[Object.clone]: https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#clone%28%29