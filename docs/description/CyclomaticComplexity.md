Checks cyclomatic complexity against a specified limit. It is a measure
of the minimum number of possible paths through the source and therefore
the number of required tests, it is not a about quality of code\! It is
only applied to methods, c-tors, [static initializers and instance
initializers](https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html).

The complexity is equal to the number of decision points `  + 1 `.
Decision points: `if`, `while` , `do`, `for`, `?:`, `catch` , `switch`,
`case` statements and operators `&&` and `||` in the body of target.

By pure theory level 1-4 is considered easy to test, 5-7 OK, 8-10
consider re-factoring to ease testing, and 11+ re-factor now as testing
will be painful.

When it comes to code quality measurement by this metric level 10 is
very good level as a ultimate target (that is hard to archive). Do not
be ashamed to have complexity level 15 or even higher, but keep it below
20 to catch really bad designed code automatically.

Please use Suppression to avoid violations on cases that could not be
split in few methods without damaging readability of code or
encapsulation.
