Checks that an overriding `finalize()` method invokes
`super.finalize()`. Does not check native methods, as they have no
possible java defined implementation.

References: [How to Handle Java Finalization\'s Memory-Retention
Issues](https://www.oracle.com/technetwork/java/javamail/finalization-137655.html);
[10 points on finalize method in
Java](https://javarevisited.blogspot.com/2012/03/finalize-method-in-java-tutorial.html).
