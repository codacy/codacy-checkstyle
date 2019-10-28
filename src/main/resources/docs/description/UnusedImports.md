Checks for unused import statements. Checkstyle uses a simple but very
reliable algorithm to report on unused import statements. An import
statement is considered unused if:

-   It is not referenced in the file. The algorithm does not support
    wild-card imports like `import             java.io.*;`. Most IDE\'s
    provide very sophisticated checks for imports that handle wild-card
    imports.
-   It is a duplicate of another import. This is when a class is
    imported more than once.
-   The class imported is from the `java.lang` package. For example
    importing `java.lang.String`.
-   The class imported is from the same package.
-   **Optionally:** it is referenced in Javadoc comments. This check is
    on by default, but it is considered bad practice to introduce a
    compile time dependency for documentation purposes only. As an
    example, the import `java.util.List` would be considered referenced
    with the Javadoc comment `{@link List}`. The alternative to avoid
    introducing a compile time dependency would be to write the Javadoc
    comment as `{@link java.util.List}`.

The main limitation of this check is handling the case where an imported
type has the same name as a declaration, such as a member variable.

For example, in the following case the import `java.awt.Component` will
not be flagged as unused:

    import java.awt.Component;
    class FooBar {
      private Object Component; // a bad practice in my opinion
      ...
    }