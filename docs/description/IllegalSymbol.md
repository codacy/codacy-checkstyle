<div>

Checks that specified symbols (by Unicode code points or ranges) are not
used in code. By default, blocks common symbol ranges.

</div>

Rationale: This check helps prevent emoji symbols and special characters
in code (commonly added by AI tools), enforce coding standards, or
forbid specific Unicode characters.

Default ranges cover:

- U+2190–U+27BF: Arrows, Mathematical Operators, Box Drawing, Geometric
  Shapes, Miscellaneous Symbols, and Dingbats
- U+1F600–U+1F64F: Emoticons
- U+1F680–U+1F6FF: Transport and Map Symbols
- U+1F700–U+10FFFF: Alchemical Symbols and other pictographic symbols

For a complete list of Unicode characters and ranges, see: [List of
Unicode
characters](https://en.wikipedia.org/wiki/List_of_Unicode_characters)

- Property `symbolCodes` - Specify the symbols to check for, as Unicode
  code points or ranges. Format: comma-separated list of hex codes or
  ranges (e.g., `"0x2705, 0x1F600-0x1F64F"`). To allow only ASCII
  characters, use `"0x0080-0x10FFFF"`. Type is `java.lang.String`.
  Default value is
  `"0x2190-0x27BF, 0x1F600-0x1F64F, 0x1F680-0x1F6FF, 0x1F700-0x10FFFF"`.
