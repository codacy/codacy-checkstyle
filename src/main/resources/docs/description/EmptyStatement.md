Detects empty statements (standalone {@code ";"} semicolon).
Empty statements often introduce bugs that are hard to spot, such as in


    if (someCondition);
      doConditionalStuff();
    doUnconditionalStuff();
            
