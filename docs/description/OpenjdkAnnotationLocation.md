<div>

Verifies that annotations are properly placed by [OpenJDK
Style](https://cr.openjdk.org/~alundblad/styleguide/index-v6.html#toc-annotations).
Declaration annotations must either reside entirely on a single line or
have each annotation placed on its own separate line. Annotations may
share a line with the target declaration only when all annotations and
the complete target declaration are on that same line.

</div>

Attention: Checkstyle ignores annotations placed among modifiers due to
a technical limitation. The parser cannot distinguish whether an
annotation applies to the method itself or to its return type.
