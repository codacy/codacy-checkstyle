Since Checkstyle 3.5

Determines complexity of methods, classes and files by counting the Non Commenting Source Statements (NCSS). This check adheres to the [ specification][specification] for the [JavaNCSS-Tool][] written by **Chr. Clemens Lee**.
Roughly said the NCSS metric is calculated by counting the source lines which are not comments, (nearly) equivalent to counting the semicolons and opening curly braces.
The NCSS for a class is summarized from the NCSS of all its methods, the NCSS of its nested classes and the number of member variable declarations.
The NCSS for a file is summarized from the ncss of all its top level classes, the number of imports and the package declaration.

Rationale: Too large methods and classes are hard to read and costly to maintain. A large NCSS number often means that a method or class has too many responsibilities and/or functionalities which should be decomposed into smaller units.


[specification]: http://www.kclee.de/clemens/java/javancss/#specification
[JavaNCSS-Tool]: http://www.kclee.de/clemens/java/javancss/