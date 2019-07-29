Checks that any combination of String literals
is on the left side of an `equals()` comparison.
Also checks for String literals assigned to some field
(such as `someString.equals(anotherString = "text")`).

Rationale: Calling the `equals()`
method on String literals will avoid a potential
`NullPointerException`. Also, it is pretty common to see null
checks right before equals comparisons, which is not necessary
in the example below.

For example, this code:


    String nullString = null;
    nullString.equals("My_Sweet_String");
            
should be refactored to:


    String nullString = null;
    "My_Sweet_String".equals(nullString);
            
