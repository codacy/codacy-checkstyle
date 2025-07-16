<div>

Checks identifiers against a regular expression pattern to detect
illegal names. Since this check uses a pattern to define *valid*
identifiers, users will need to use negative lookaheads to explicitly
ban certain names (e.g., "var") or patterns (e.g., any identifier
containing $) while still allowing all other valid identifiers.

</div>
