<div>

Checks that all numerical literal prefixes, infixes, and suffixes are
written using lowercase letters (for example prefixes : `0x, 0b`,
infixes : `e, p`, suffixes : `f, d`). This convention follows the
[OpenJDK Style
Guide](https://cr.openjdk.org/~alundblad/styleguide/index-v6.html#toc-literals).

</div>

**ATTENTION:**

This check ignores the `L` suffix for long literals, as using an
uppercase `L` is a widely accepted and recommended practice to avoid
confusion with the digit `1`.
