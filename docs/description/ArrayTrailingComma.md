<div>

Checks that array initialization contains a trailing comma.

</div>

<div class="wrapper">

``` prettyprint
int[] a = new int[]
{
  1,
  2,
  3,
};
        
```

</div>

By default, the check demands a comma at the end if neither left nor
right curly braces are on the same line as the last element of the
array.

<div class="wrapper">

``` prettyprint
return new int[] { 0 };
return new int[] { 0
  };
return new int[] {
  0 };
        
```

</div>

Rationale: Putting this comma in makes it easier to change the order of
the elements or add new elements on the end. Main benefit of a trailing
comma is that when you add new entry to an array, no surrounding lines
are changed.

<div class="wrapper">

``` prettyprint
{
  100000000000000000000,
  200000000000000000000, // OK
}

{
  100000000000000000000,
  200000000000000000000,
  300000000000000000000,  // Just this line added, no other changes
}
        
```

</div>

If closing brace is on the same line as trailing comma, this benefit is
gone (as the check does not demand a certain location of curly braces
the following two cases will not produce a violation):

<div class="wrapper">

``` prettyprint
{100000000000000000000,
 200000000000000000000,} // Trailing comma not needed, line needs to be modified anyway

{100000000000000000000,
 200000000000000000000, // Modified line
 300000000000000000000,} // Added line
        
```

</div>

If opening brace is on the same line as trailing comma there's also
(more arguable) problem:

<div class="wrapper">

``` prettyprint
{100000000000000000000, // Line cannot be just duplicated to slightly modify entry
}

{100000000000000000000,
 100000000000000000001, // More work needed to duplicate
}
        
```

</div>
