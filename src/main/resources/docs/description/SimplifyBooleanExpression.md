Since Checkstyle 3.0

Checks for over-complicated boolean expressions. Currently finds code like `if (b == true)`, `b || true`, `!false`, etc.

Rationale: Complex boolean logic makes code hard to understand and maintain.