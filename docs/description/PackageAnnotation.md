Checks that all package annotations are in the package-info.java file.

For Java SE8 and above, placement of package annotations in the
package-info.java file is enforced by the compiler and this check is not
necessary.

For Java SE7 and below, the Java Language Specification highly
recommends but doesn't require that annotations are placed in the
package-info.java file, and this check can help to enforce that
placement.

See [Java Language Specification,
§7.4.1](https://docs.oracle.com/javase/specs/jls/se11/html/jls-7.html#jls-7.4.1)
for more info.
