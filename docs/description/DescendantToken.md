<div>

Checks for restricted tokens beneath other tokens.

</div>

WARNING: This is a very powerful and flexible check, but, at the same
time, it is low-level and very implementation-dependent because its
results depend on the grammar we use to build abstract syntax trees.
Thus, we recommend using other checks when they provide the desired
functionality. Essentially, this check just works on the level of an
abstract syntax tree and knows nothing about language structures.
