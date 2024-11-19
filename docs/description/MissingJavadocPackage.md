<div>

Checks for missing package definition Javadoc comments in
package-info.java files.

</div>

Rationale: description and other related documentation for a package can
be written up in the package-info.java file and it gets used in the
production of the Javadocs. See
[link](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html#packagecomment)
for more info.

This check specifically only validates package definitions. It will not
validate any methods or interfaces declared in the package-info.java
file.
