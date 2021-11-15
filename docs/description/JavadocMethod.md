Checks the Javadoc of a method or constructor.

Violates parameters and type parameters for which no param tags are
present can be suppressed by defining property `allowMissingParamTags`.

Violates methods which return non-void but for which no return tag is
present can be suppressed by defining property `allowMissingReturnTag`.

Violates exceptions which are declared to be thrown (by `throws` in the
method signature or by `throw new` in the method body), but for which no
throws tag is present by activation of property `validateThrows`. Note
that `throw new` is not checked in the following places:

  - Inside a try block (with catch). It is not possible to determine if
    the thrown exception can be caught by the catch block as there is no
    knowledge of the inheritance hierarchy, so the try block is ignored
    entirely. However, catch and finally blocks, as well as try blocks
    without catch, are still checked.
  - Local classes, anonymous classes and lambda expressions. It is not
    known when the throw statements inside such classes are going to be
    evaluated, so they are ignored.

ATTENTION: Checkstyle does not have information about hierarchy of
exception types so usage of base class is considered as separate
exception type. As workaround you need to specify both types in javadoc
(parent and exact type).

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
