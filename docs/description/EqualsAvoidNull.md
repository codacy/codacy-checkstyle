<div>

Checks that any combination of String literals is on the left side of an
`equals()` comparison. Also checks for String literals assigned to some
field (such as `someString.equals(anotherString = "text")`).

</div>

Rationale: Calling the `equals()` method on String literals will avoid a
potential `NullPointerException`. Also, it is pretty common to see null
checks right before equals comparisons but following this rule such
checks are not required.
