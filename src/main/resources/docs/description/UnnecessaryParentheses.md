Checks if unnecessary parentheses are used in a statement or expression.
The check will flag the following with warnings:

``` 
    return (x);          // parens around identifier
    return (x + 1);      // parens around return value
    int x = (y / 2 + 1); // parens around assignment rhs
    for (int i = (0); i < 10; i++) {  // parens around literal
    t -= (z + 1);        // parens around assignment rhs
        
```
