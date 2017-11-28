Since Checkstyle 3.2

Checks that particular classes are never used as types in variable declarations, one-dimensional and multi-dimensional arrays, return values or parameters.

Rationale: Helps reduce coupling on concrete classes.

For additional restriction of type usage see also: IllegalInstantiation, IllegalImport