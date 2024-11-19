<div>

Checks for long anonymous inner classes.

</div>

Rationale: If an anonymous inner class becomes very long it is hard to
understand and to see the flow of the method where the class is defined.
Therefore, long anonymous inner classes should usually be refactored
into a named inner class. See also Bloch, Effective Java, p. 93.
