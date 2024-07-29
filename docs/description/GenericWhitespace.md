Checks that the whitespace around the Generic tokens (angle brackets)
"\<" and "\>" are correct to the *typical* convention. The convention is
not configurable.

Left angle bracket ("\<"):

- should be preceded with whitespace only in generic methods
  definitions.
- should not be preceded with whitespace when it is preceded method name
  or constructor.
- should not be preceded with whitespace when following type name.
- should not be followed with whitespace in all cases.

Right angle bracket ("\>"):

- should not be preceded with whitespace in all cases.
- should be followed with whitespace in almost all cases, except diamond
  operators and when preceding a method name, constructor, or record
  header.
