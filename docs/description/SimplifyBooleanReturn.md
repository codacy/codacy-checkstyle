<div>

Checks for over-complicated boolean return or yield statements. For
example the following code

</div>

    if (valid())
      return false;
    else
      return true;
            

could be written as

    return !valid();
            

The idea for this Check has been shamelessly stolen from the equivalent
[PMD](https://pmd.github.io/pmd/pmd_rules_java_design.html#simplifybooleanreturns)
rule.
