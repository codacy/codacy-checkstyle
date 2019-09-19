Checks if any class or object member is explicitly initialized to
default for its type value (`null` for object references, zero for
numeric types and `char` and `false` for `boolean`.

Rationale: Each instance variable gets initialized twice, to the same
value. Java initializes each instance variable to its default value (`0`
or `null`) before performing any initialization specified in the code.
So there is a minor inefficiency.