<div>

Checks that Javadoc inline tags are preferred over escaping entities.
According to [OpenJDK Style Guidelines
v6](https://cr.openjdk.org/~alundblad/styleguide/index-v6.html) Javadoc
inline tags should be preferred over their HTML equivalents. Entities
that are flagged by the check are:

- &lt;
- &gt;
- &amp;
- &quot;
- &apos;

Reason of only these entities are flagged is given here : [Predefined
Entities](https://www.w3.org/TR/WD-xml-970807#dt-escape)

</div>

**Not flagged:**

- Content inside `<pre>` and `<code>` blocks (code examples)
- Content inside `{@code}`, `{@literal}`, `{@snippet}` inline tags
