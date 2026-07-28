<div>

Checks correct format of [Java Text
Blocks](https://docs.oracle.com/en/java/javase/17/text-blocks/index.html)
as specified in [Google Java Style
Guide](https://google.github.io/styleguide/javaguide.html#s4.8.9-text-blocks).

</div>

This Check performs two validations:

1.  It ensures that the opening and closing text-block quotes (`"""`)
    each appear on their own line, with no other item preceding them.
2.  Opening and closing quotes are vertically aligned.
3.  Each line of text in the text block must be indented at least as
    much as the opening and closing quotes.

Note: Closing quotes can be followed by additional code on the same
line.
