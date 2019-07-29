Detects inline conditionals. Here is one example of an inline conditional:


    String a = getParameter("a");
    String b = (a==null || a.length<1) ? null : a.substring(1);
            
Rationale: Some developers find inline conditionals hard to read, so
their employer's coding standards forbid them.
