<div>

Checks the header of a source file against a header that contains a
[pattern](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/regex/Pattern.html)
for each line of the source header.

</div>

Rationale: In some projects [checking against a fixed
header](https://checkstyle.org/header.html#Header) is not sufficient,
e.g. the header might require a copyright line where the year
information is not static.

For example, consider the following header:

    line  1: ^/{71}$
    line  2: ^// checkstyle:$
    line  3: ^// Checks Java source code for adherence to a set of rules\.$
    line  4: ^// Copyright \(C\) \d\d\d\d  Oliver Burn$
    line  5: ^// Last modification by \$Author.*\$$
    line  6: ^/{71}$
    line  7:
    line  8: ^package
    line  9:
    line 10: ^import
    line 11:
    line 12: ^/\*\*
    line 13: ^ \*([^/]|$)
    line 14: ^ \*/
            

Lines 1 and 6 demonstrate a more compact notation for 71 '/' characters.
Line 4 enforces that the copyright notice includes a four digit year.
Line 5 is an example how to enforce revision control keywords in a file
header. Lines 12-14 is a template for javadoc (line 13 is so complicated
to remove conflict with and of javadoc comment). Lines 7, 9 and 11 will
be treated as '^$' and will forcefully expect the line to be empty.

Different programming languages have different comment syntax rules, but
all of them start a comment with a non-word character. Hence, you can
often use the non-word character class to abstract away the concrete
comment syntax and allow checking the header for different languages
with a single header definition. For example, consider the following
header specification (note that this is not the full Apache license
header):

    line 1: ^#!
    line 2: ^<\?xml.*>$
    line 3: ^\W*$
    line 4: ^\W*Copyright 2006 The Apache Software Foundation or its licensors, as applicable\.$
    line 5: ^\W*Licensed under the Apache License, Version 2\.0 \(the "License"\);$
    line 6: ^\W*$
            

Lines 1 and 2 leave room for technical header lines, e.g. the
"#!/bin/sh" line in Unix shell scripts, or the XML file header of XML
files. Set the multiline property to "1, 2" so these lines can be
ignored for file types where they do no apply. Lines 3 through 6 define
the actual header content. Note how lines 2, 4 and 5 use escapes for
characters that have special regexp semantics.

In default configuration, if header is not specified, the default value
of header is set to null and the check does not rise any violations.
