<div>

Checks that non-constant field names conform to the [Google Java Style
Guide](https://google.github.io/styleguide/javaguide.html#s5.2.5-non-constant-field-names)
for non-constant field naming.

</div>

This check enforces Google's specific non-constant field naming
requirements:

- Non-constant field names must start with a lowercase letter and use
  uppercase letters for word boundaries.
- Underscores may be used to separate adjacent numbers (e.g., version
  numbers like `guava33_4_5`), but NOT between letters and digits.

Static fields are skipped because Checkstyle cannot determine type
immutability to distinguish constants from non-constants. Fields in
interfaces and annotations are also skipped because they are implicitly
`public static final` (constants)
