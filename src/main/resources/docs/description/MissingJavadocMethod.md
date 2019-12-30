Checks for missing Javadoc comments for a method or constructor. The
scope to verify is specified using the `Scope` class and defaults to
`Scope.PUBLIC`. To verify another scope, set property scope to a
different [scope](https://checkstyle.org/property_types.html#scope).

Javadoc is not required on a method that is tagged with the `@Override`
annotation. However under Java 5 it is not possible to mark a method
required for an interface (this was *corrected* under Java 6). Hence
Checkstyle supports using the convention of using a single
`{@inheritDoc}` tag instead of all the other tags.

For getters and setters for the property `allowMissingPropertyJavadoc`,
the methods must match exactly the structures below.

::: {.wrapper}
    public void setNumber(final int number)
    {
        mNumber = number;
    }

    public int getNumber()
    {
        return mNumber;
    }

    public boolean isSomething()
    {
        return false;
    }
              
:::