<div>

Verifies that annotations are properly placed by [OpenJDK
Style](https://cr.openjdk.org/~alundblad/styleguide/index-v6.html#toc-annotations).
Declaration annotations must either reside entirely on a single line or
have each annotation placed on its own separate line. Annotations should
not share a line with the target declaration, except for single-line
methods and fields.

</div>

Attention: Checkstyle ignores annotations placed among modifiers due to
a technical limitation. The parser cannot distinguish whether an
annotation applies to the method itself or to its return type.
