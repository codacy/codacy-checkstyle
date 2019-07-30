Since Checkstyle 3.0

Checks that there are no import statements that use the \* notation.

Rationale: Importing all classes from a package or static members from a
class leads to tight coupling between packages or classes and might lead
to problems when a new version of a library introduces name clashes.
