<div>

Checks for fall-through in `switch` statements. Finds locations where a
`case` **contains** Java code but lacks a `break`, `return`, `yield`,
`throw` or `continue` statement.

</div>

The check honors special comments to suppress the warning. By default,
the texts "fallthru", "fall thru", "fall-thru", "fallthrough", "fall
through", "fall-through" "fallsthrough", "falls through",
"falls-through" (case-sensitive). The comment containing these words
must be all on one line, and must be on the last non-empty line before
the `case` triggering the warning or on the same line before the `case`
(ugly, but possible). Any other comment may follow on the same line.

Note: The check assumes that there is no unreachable code in the `case`.
