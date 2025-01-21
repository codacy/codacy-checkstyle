<div>

Checks the Javadoc paragraph.

</div>

Checks that:

- There is one blank line between each of two paragraphs.
- Each paragraph but the first has \<p\> immediately before the first
  word, with no space after.
- The outer most paragraph tags should not precede [HTML
  block-tag](https://www.w3schools.com/html/html_blocks.asp). Nested
  paragraph tags are allowed to do that. This check only supports
  following block-tags: \<address\>,\<blockquote\> ,\<div\>,\<dl\>
  ,\<h1\>,\<h2\>,\<h3\>,\<h4\>,\<h5\>,\<h6\>,\<hr\>
  ,\<ol\>,\<p\>,\<pre\> ,\<table\>,\<ul\>.

**ATTENTION:**

This Check ignores HTML comments.

The Check ignores all the nested paragraph tags, it will not give any
kind of violation if the paragraph tag is nested.
