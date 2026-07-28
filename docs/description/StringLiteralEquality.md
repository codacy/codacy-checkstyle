<div>

Checks that string literals are not used with `==` or `!=`. Since `==`
will compare the object references, not the actual value of the strings,
`String.equals()` should be used. More information can be found [in this
article](https://stackoverflow.com/questions/513832/how-do-i-compare-strings-in-java/).

</div>

Rationale: Novice Java programmers often use code like:

<div class="wrapper">

``` prettyprint
if (x == "something")
        
```

</div>

when they mean

<div class="wrapper">

``` prettyprint
if ("something".equals(x))
        
```

</div>
