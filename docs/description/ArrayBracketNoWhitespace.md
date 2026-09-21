<div>

Checks that the whitespace around square-bracket tokens `[` and `]`
follows the Google Java Style Guide requirements for array declarations,
array creation, and array indexing as described in [Section 4.6.2:
Horizontal
Whitespace](https://google.github.io/styleguide/javaguide.html#s4.6.2-horizontal-whitespace).

</div>

Left square bracket ("`[`"):

- must not be preceded with whitespace when preceded by a `TYPE` or
  `IDENT` in array declarations or array access
- must not be followed with whitespace

Right square bracket ("`]`"):

- must not be preceded with whitespace
- must be followed with whitespace in all cases, except when followed
  by:
  - another bracket: `[][]`
  - a dot for member access: `arr[i].length`
  - a comma or semicolon: `arr[i],` or `arr[i];`
  - postfix operators: `arr[i]++` or `arr[i]--`
  - a right parenthesis or another closing construct: `(arr[i])`
