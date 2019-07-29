Since Checkstyle 6.9

Checks that a source file begins with a specified header. Property
` headerFile` specifies a file that contains
the required header. Alternatively, the header specification can be
set directly in the `header` property
without the need for an external file.

Property `ignoreLines` specifies the line
numbers to ignore when matching lines in a header file. This
property is very useful for supporting headers that contain
copyright dates. For example, consider the following header:


    line 1: ////////////////////////////////////////////////////////////////////
    line 2: // checkstyle:
    line 3: // Checks Java source code for adherence to a set of rules.
    line 4: // Copyright (C) 2002  Oliver Burn
    line 5: ////////////////////////////////////////////////////////////////////
            
Since the year information will change over time, you can tell
Checkstyle to ignore line 4 by setting property `ignoreLines` to
` 4`.
