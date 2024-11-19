<div>

Checks that all constructors are grouped together. If there is any
non-constructor code separating constructors, this check identifies and
logs a violation for those ungrouped constructors. The violation message
will specify the line number of the last grouped constructor. Comments
between constructors are allowed.

</div>

Rationale: Grouping constructors together in a class improves code
readability and maintainability. It allows developers to easily
understand the different ways an object can be instantiated and the
tasks performed by each constructor.
