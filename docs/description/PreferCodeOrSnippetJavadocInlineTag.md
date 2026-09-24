<div>

Checks that Javadoc inline tags `{@code ...}` and `{@snippet ...}` are
preferred over HTML tags `<code>` and `<pre>`.

</div>

This check enforces using either `{@code ...}` or `{@snippet ...}`
inline tags instead of single-line `<code>` and `<pre>` HTML tags, and
using `{@snippet ...}` inline tags instead of multi-line `<code>` and
`<pre>` HTML tags.

Per [OpenJDK Style Guidelines
v6](https://cr.openjdk.org/~alundblad/styleguide/index-v6.html), Javadoc
inline tags should be preferred over their HTML equivalents.

To suppress violation for snippet inline tag:

<div class="wrapper">

``` prettyprint
    <module name="SuppressionSingleFilter">
      <property name="checks" value="PreferCodeOrSnippetJavadocInlineTag"/>
      <property name="files" value="file-name"/>
      <property name="message" value="Use snippet inline tag instead of.*"/>
    </module>
        
```

</div>

**Not Flagged :**

- Tags which have unbalanced curly braces
- Tags which have content that starts with star.
- Tags which are inside other tags
