Since Checkstyle 3.2

Checks that an overriding `finalize()` method invokes `super.finalize()`. Does not check native methods, as they have no possible java defined implementation.

Reference: [ Use Finalization Only When You Must][Use Finalization Only When You Must].


[Use Finalization Only When You Must]: http://www.oracle.com/technetwork/java/javamail/finalization-137655.html