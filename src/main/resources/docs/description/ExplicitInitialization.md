Since Checkstyle 3.2

Checks if any class or object member is explicitly initialized to default for its type value (`null` for object references, zero for numeric types and `char` and `false` for `boolean`.

Rationale: Each instance variable gets initialized twice, to the same value. Java initializes each instance variable to its default value (0 or null) before performing any initialization specified in the code. So in this case, x gets initialized to 0 twice, and bar gets initialized to null twice. So there is a minor inefficiency. This style of coding is a holdover from C/C++ style coding, and it shows that the developer isn't really confident that Java initializes instance variables to default values.