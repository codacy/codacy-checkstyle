Check for ensuring that for loop control variables are not modified inside
the for block. An example is:


    for (int i = 0; i < 1; i++) {
      i++; //violation
    }
            
Rationale: If the control variable is modified inside the loop
body, the program flow becomes more difficult to follow. See
[FOR statement](https://docs.oracle.com/javase/specs/jls/se11/html/jls-14.html#jls-14.14) specification for more details.

Such loop would be suppressed:


    for (int i = 0; i < 10;) {
      i++;
    }
            
