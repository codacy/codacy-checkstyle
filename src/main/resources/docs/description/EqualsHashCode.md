Checks that classes that either override `equals()`
or `hashCode()` also overrides the other.
This check only verifies that the method declarations match
`Object.equals(Object)` and `Object.hashCode()` exactly to be
considered an override. This check does not verify invalid method names, parameters
other than `Object`, or anything else.

Rationale: The contract of `equals()` and
`hashCode()` requires that equal objects
have the same hashCode. Therefore, whenever you override
`equals()` you must override `hashCode()`
to ensure that your class can be used in hash-based collections.
