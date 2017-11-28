Since Checkstyle 3.2

Ensures that a class has a package declaration, and (optionally) whether the package name matches the directory name for the source file.

Rationale: Classes that live in the null package cannot be imported. Many novice developers are not aware of this.

Packages provide logical namespace to classes and should be stored in the form of directory levels to provide physical grouping to your classes. These directories are added to the classpath so that your classes are visible to JVM when it runs the code.