Since Checkstyle 3.0

Checks for over-complicated boolean return statements. For example the following code

    if (valid())
      return false;
    else
      return true;

could be written as

    return !valid();

The idea for this Check has been shamelessly stolen from the equivalent [PMD][] rule.


[PMD]: http://pmd.sourceforge.net