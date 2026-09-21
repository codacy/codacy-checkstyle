<div>

Checks for fully qualified type references that are unnecessary because
the type could simply be imported and referenced by its simple name.

</div>

A fully qualified type reference, such as `java.util.Map`, is reported
as unnecessary unless another type with the same simple name is also
used in the file. For example, `java.util.Map` is allowed only when a
different `Map` (e.g. `com.example.Map`, a locally declared `Map`, or a
same-package or `java.lang` `Map`) is also referenced, since in that
case qualification is required to tell the two types apart.

Using fully qualified names where they are not needed reduces
readability and is inconsistent with common Java style conventions,
which favor importing the type instead.

The following limitations apply:

- To distinguish a package qualified type (such as `java.util.Map`) from
  a reference to a nested type (such as `Map.Entry`), the check relies
  on the common convention that package names start with a lowercase
  letter. A qualified reference is treated as fully qualified only when
  the identifier preceding the simple name starts with a lowercase
  letter.
- References to nested types via an enclosing type (for example
  `java.util.Map.Entry`) and fully qualified annotations (for example
  `@java.lang.Override`) are not reported.
- On-demand (wildcard) imports, such as `import java.util.*;`, are
  checked against classes reachable on this check's own class loader.
  This reliably detects collisions from JDK packages (for example a
  fully qualified reference to a third-party `Observer` type being left
  alone because `import java.util.*;` also brings a different
  `java.util.Observer` into scope), but on-demand imports of third-party
  or project-local packages cannot be resolved this way and may still be
  flagged incorrectly.
