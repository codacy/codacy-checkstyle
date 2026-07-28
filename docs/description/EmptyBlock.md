<div>

Checks for empty blocks.

</div>

This check does not validate sequential blocks. This check does not
violate fallthrough.

NOTE: This check processes LITERAL_CASE and LITERAL_DEFAULT separately.
Verification empty block is done for single nearest `case` or `default`.
