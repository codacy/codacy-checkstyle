The Check validate abbreviations(consecutive capital letters)
length in identifier name, it also allows to enforce camel case naming. Please read more at
[Google Style Guide](https://checkstyle.org/styleguides/google-java-style-20180523/javaguide.html#s5.3-camel-case)
to get to know how to avoid long abbreviations in names.

`allowedAbbreviationLength` specifies how many consecutive capital letters are
allowed in the identifier.
A value of *3* indicates that up to 4 consecutive capital letters are allowed,
one after the other, before a violation is printed. The identifier 'MyTEST' would be
allowed, but 'MyTESTS' would not be.
A value of *0* indicates that only 1 consecutive capital letter is allowed. This
is what should be used to enforce strict camel casing. The identifier 'MyTest' would
be allowed, but 'MyTEst' would not be.
