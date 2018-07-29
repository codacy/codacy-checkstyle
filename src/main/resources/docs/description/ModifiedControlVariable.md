Since Checkstyle 3.5

Check for ensuring that for loop control variables are not modified inside the for block. An example is:

    for (int i = 0; i < 1; i++) {
      i++; //violation
    }

Rationale: If the control variable is modified inside the loop body, the program flow becomes more difficult to follow.
See [ FOR statement][FOR statement] specification for more details.

Such loop would be suppressed:

    for (int i = 0; i < 10;) {
      i++;
    }


[FOR statement]: https://docs.oracle.com/javase/specs/jls/se8/html/jls-14.html#jls-14.14