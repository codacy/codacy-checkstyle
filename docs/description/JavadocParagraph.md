<div>

Checks the Javadoc paragraph.

</div>

Checks that:

- There is one blank line between each of two paragraphs.
- Each paragraph but the first has \<p\> immediately before the first
  word, with no space after.
- First paragraph tag should not precede [HTML
  block-tag](https://www.w3schools.com/html/html_blocks.asp), nested
  paragraph tags are allowed to do that.

**ATTENTION:**

This Check ignores HTML comments.

The Check ignores all the nested paragraph tags, it will not give any
kind of violation if the paragraph tag is nested.
