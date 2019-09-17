The check to ensure that requires that comments be the only thing on a
line. For the case of `//` comments that means that the only thing that
should precede it is whitespace. It doesn\'t check comments if they do
not end a line; for example, it accepts the following:
`Thread.sleep( 10 /*some comment here*/ );` Format property is intended
to deal with the `} // while` example.

Rationale: Steve McConnell in Code Complete suggests that endline
comments are a bad practice. An end line comment would be one that is on
the same line as actual code. For example:

    a = b + c;      // Some insightful comment
    d = e / f;        // Another comment for this line
            

Quoting Code Complete for the justification:

-   \"The comments have to be aligned so that they do not interfere with
    the visual structure of the code. If you don\'t align them neatly,
    they\'ll make your listing look like it\'s been through a washing
    machine.\"
-   \"Endline comments tend to be hard to format\...It takes time to
    align them. Such time is not spent learning more about the code;
    it\'s dedicated solely to the tedious task of pressing the spacebar
    or tab key.\"
-   \"Endline comments are also hard to maintain. If the code on any
    line containing an endline comment grows, it bumps the comment
    farther out, and all the other endline comments will have to bumped
    out to match. Styles that are hard to maintain aren\'t
    maintained\....\"
-   \"Endline comments also tend to be cryptic. The right side of the
    line doesn\'t offer much room and the desire to keep the comment on
    one line means the comment must be short. Work then goes into making
    the line as short as possible instead of as clear as possible. The
    comment usually ends up as cryptic as possible\....\"
-   \"A systemic problem with endline comments is that it\'s hard to
    write a meaningful comment for one line of code. Most endline
    comments just repeat the line of code, which hurts more than it
    helps.\"

McConnell\'s comments on being hard to maintain when the size of the
line changes are even more important in the age of automated
refactorings.
