Since Checkstyle 3.0

Checks for assignments in subexpressions, such as in `String s = Integer.toString(i = 2);`.

Rationale: With the exception of `for` iterators and assignment in `while` idiom, all assignments should occur in their own top-level statement to increase readability. With inner assignments like the one given above, it is difficult to see all places where a variable is set.

Note: Check allows usage of the popular assignment in `while` idiom:

    String line;
    while ((line = bufferedReader.readLine()) != null) {
       // process the line
    }

Assignment inside a condition is not a problem here, as the assignment is surrounded by an extra pair of parentheses. The comparison is `!= null` and there is no chance that intention was to write `line == reader.readLine()`.