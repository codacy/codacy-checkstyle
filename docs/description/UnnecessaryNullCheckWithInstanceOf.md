<div>

Checks for redundant null checks with the instanceof operator.

</div>

The instanceof operator inherently returns false when the left operand
is null, making explicit null checks redundant in boolean expressions
with instanceof.
