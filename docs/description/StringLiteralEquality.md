Checks that string literals are not used with `==` or `!=`. Since `==`
will compare the object references, not the actual value of the strings,
`String.equals()` should be used. More information can be found [in this
article](http://www.thejavageek.com/2013/07/27/string-comparison-with-equals-and-assignment-operator/).

Rationale: Novice Java programmers often use code like:

    if (x == "something")
            

when they mean

    if ("something".equals(x))
