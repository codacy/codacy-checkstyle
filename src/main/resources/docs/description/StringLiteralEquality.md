Checks that string literals are not used with `==` or `!=`.

Rationale: Novice Java programmers often use code like:

    if (x == "something")
            

when they mean

    if ("something".equals(x))
            
