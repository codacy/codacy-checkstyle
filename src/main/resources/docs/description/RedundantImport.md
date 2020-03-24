Checks for redundant import statements. An import statement is
considered redundant if:

  - It is a duplicate of another import. This is, when a class is
    imported more than once.
  - The class non-statically imported is from the `java.lang` package,
    e.g. importing `java.lang.String`.
  - The class non-statically imported is from the same package as the
    current package.
