<div>

Checks for over-complicated boolean return or yield statements. For
example the following code

</div>

<div class="wrapper">

``` prettyprint
if (valid())
  return false;
else
  return true;
        
```

</div>

could be written as

<div class="wrapper">

``` prettyprint
return !valid();
        
```

</div>

The idea for this Check has been shamelessly stolen from the equivalent
[PMD](https://pmd.github.io/pmd/pmd_rules_java_design.html#simplifybooleanreturns)
rule.
