Verifies that the `@Override` annotation is present when the
`@inheritDoc` javadoc tag is present.

Rationale: The @Override annotation helps compiler tools ensure that an
override is actually occurring. It is quite easy to accidentally
overload a method or hide a static method and using the @Override
annotation points out these problems.

This check will log a violation if using the @inheritDoc tag on a method
that is not valid (ex: private, or static method).

There is a slight difference between the @Override annotation in Java 5
versus Java 6 and above. In Java 5, any method overridden from an
interface cannot be annotated with @Override. In Java 6 this behavior is
allowed.

As a result of the aforementioned difference between Java 5 and Java 6,
a property called `javaFiveCompatibility` is available. This property
will only check classes, interfaces, etc. that do not contain the
extends or implements keyword or are not anonymous classes. This means
it only checks methods overridden from `java.lang.Object`. **Java 5
Compatibility mode severely limits this check. It is recommended to only
use it on Java 5 source.**
