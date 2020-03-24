Checks the Javadoc of a method or constructor. The scope to verify is
specified using the `Scope` class and defaults to `Scope.PRIVATE`. To
verify another scope, set property scope to a different
[scope](https://checkstyle.org/property_types.html#scope).

Violates parameters and type parameters for which no param tags are
present can be suppressed by defining property `allowMissingParamTags`.
Violates methods which return non-void but for which no return tag is
present can be suppressed by defining property `allowMissingReturnTag`.
Violates exceptions which are declared to be thrown, but for which no
throws tag is present by activation of property `validateThrows`.

Javadoc is not required on a method that is tagged with the `@Override`
annotation. However under Java 5 it is not possible to mark a method
required for an interface (this was *corrected* under Java 6). Hence
Checkstyle supports using the convention of using a single
`{@inheritDoc}` tag instead of all the other tags.

Note that only inheritable items will allow the `{@inheritDoc}` tag to
be used in place of comments. Static methods at all visibilities,
private non-static methods and constructors are not inheritable.

For example, if the following method is implementing a method required
by an interface, then the Javadoc could be done as:

``` 
/** {@inheritDoc} */
public int checkReturnTag(final int aTagIndex,
                          JavadocTag[] aTags,
                          int aLineNo)
        
```
