<div>

Checks that array initialization do not contain a trailing comma.
Rationale: JLS allows trailing commas in arrays and enumerations, but
does not allow them in other locations. To unify the coding style, the
use of trailing commas should be prohibited.

</div>

    int[] foo = new int[] {
      1,
      2
    };
            

The check demands that there should not be any comma after the last
element of an array.

    String[] foo = new String[] {
      "FOO",
      "BAR", //violation
    }
