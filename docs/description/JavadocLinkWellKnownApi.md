<div>

Checks that Javadoc comments avoid unnecessary `{@link}` and
`{@linkplain}` tags for APIs that are considered well-known. Linking
well-known APIs can make comments harder to read without adding much
value for the reader.

</div>

This check reports `{@link}` references to configured well-known APIs.
Two properties are supported: `wellKnownQualifiedPackages` and
`wellKnownSimpleNames`.

Both properties are needed because Checkstyle does not resolve Javadoc
link targets. For example, `java.lang.String` contains the package name,
so it can be matched through `wellKnownQualifiedPackages`. However,
`String` only contains the simple name `String`, so it needs to be
matched through `wellKnownSimpleNames`. Resolution of imports is not a
solution since `java.lang` is implicitly imported.

For `wellKnownQualifiedPackages`, only references to classes that are
direct members of a well-known package are reported. References to a
member (for example, `String#length()`), a nested class (for example,
`System.Logger`), a subpackage (for example,
`java.lang.ref.WeakReference`), and a package itself (for example,
`java.lang.ref`) are not reported.
