<div>

Validates Javadoc comments to help ensure they are well formed.

</div>

The following checks are performed:

- Ensures the first sentence ends with proper punctuation (That is a
  period, question mark, or exclamation mark, by default). Note that
  this check is not applied to inline `@return` tags, because the
  Javadoc tools automatically appends a period to the end of the tag
  content. Javadoc automatically places the first sentence in the method
  summary table and index. Without proper punctuation the Javadoc may be
  malformed. All items eligible for the `{@inheritDoc}` tag are exempt
  from this requirement.
- Check text for Javadoc statements that do not have any description.
  This includes both completely empty Javadoc, and Javadoc with only
  tags such as `@param` and `@return`.
- Check text for incomplete HTML tags. Verifies that HTML tags have
  corresponding end tags and issues an "Unclosed HTML tag found:" error
  if not. An "Extra HTML tag found:" error is issued if an end tag is
  found without a previous open tag.
- Check that a package Javadoc comment is well-formed (as described
  above).
- Check for allowed HTML tags. The list of allowed HTML tags is "a",
  "abbr", "acronym", "address", "area", "b", "bdo", "big", "blockquote",
  "br", "caption", "cite", "code", "colgroup", "dd", "del", "dfn",
  "div", "dl", "dt", "em", "fieldset", "font", "h1", "h2", "h3", "h4",
  "h5", "h6", "hr", "i", "img", "ins", "kbd", "li", "ol", "p", "pre",
  "q", "samp", "small", "span", "strong", "sub", "sup", "table",
  "tbody", "td", "tfoot", "th", "thead", "tr", "tt", "u", "ul", "var".

These checks were patterned after the checks made by the
[DocCheck](https://maven-doccheck.sourceforge.net) doclet available from
Sun. Note: Original Sun's DocCheck tool does not exist anymore.
