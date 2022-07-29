Checks for over-complicated boolean expressions. Currently, it finds
code like ` if (b == true)`, `b || true`, `!false`,
`boolean a = q > 12 ? true : false`, etc.

Rationale: Complex boolean logic makes code hard to understand and
maintain.
