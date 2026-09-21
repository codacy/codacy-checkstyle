<div>

Checks the ordering, grouping and separation of directives in a module
declaration. Directives of each kind must form a single block, the
blocks must appear in a configurable order, and each block must be
separated from the previous one by exactly one blank line.

</div>

The default configuration enforces [Google Java Style Guide, Section
3.5.1](https://google.github.io/styleguide/javaguide.html#s3.5-module-declaration):
all `requires` directives first, then `exports`, `opens`, `uses` and
`provides`, each kind in a single block, with a single blank line
between blocks. Blank lines are what delimit blocks, so blank lines
between directives of the same kind are also violations.

All forms of `requires` (plain, `transitive`, `static`) belong to a
single block, and the order of directives inside a block is not
validated.

Directive kinds that are not listed in the `order` property are not
validated.
