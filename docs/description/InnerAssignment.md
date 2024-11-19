<div>

Checks for assignments in subexpressions, such as in
`String s = Integer.toString(i = 2);`.

</div>

Rationale: Except for the loop idioms, all assignments should occur in
their own top-level statement to increase readability. With inner
assignments like the one given above, it is difficult to see all places
where a variable is set.

Note: Check allows usage of the popular assignments in loops:

    String line;
    while ((line = bufferedReader.readLine()) != null) { // OK
      // process the line
    }

    for (;(line = bufferedReader.readLine()) != null;) { // OK
      // process the line
    }

    do {
      // process the line
    }
    while ((line = bufferedReader.readLine()) != null); // OK
            

Assignment inside a condition is not a problem here, as the assignment
is surrounded by an extra pair of parentheses. The comparison is
`!= null` and there is no chance that intention was to write
`line == reader.readLine()`.
