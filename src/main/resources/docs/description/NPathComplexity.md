Checks the NPATH complexity against a specified limit.

The NPATH metric computes the number of possible execution paths through
a function(method). It takes into account the nesting of conditional
statements and multi-part boolean expressions (A && B, C \|\| D, E ? F
:G and their combinations).

The NPATH metric was designed base on Cyclomatic complexity to avoid
problem of Cyclomatic complexity metric like nesting level within a
function(method).

Metric was described at [\"NPATH: a measure of execution pathcomplexity
and its applications\"](http://dl.acm.org/citation.cfm?id=42379). If you
need detailed description of algorithm, please read that article, it is
well written and have number of examples and details.

Here is some quotes:

> An NPATH threshold value of 200 has been established for a function.
> The value 200 is based on studies done at AT&T Bell Laboratories
> \[1988 year\].

> Some of the most effective methods of reducing the NPATH value
> include:
>
> -   distributing functionality;
> -   implementing multiple if statements as a switch statement;
> -   creating a separate function for logical expressions with a high
>     count of variables and (&&) and or (\|\|) operators.

> Although strategies to reduce the NPATH complexity of functions are
> important, care must be taken not to distort the logical clarity of
> the software by applying a strategy to reduce the complexity of
> functions. That is, there is a point of diminishing return beyond
> which a further attempt at reduction of complexity distorts the
> logical clarity of the system structure.

  Structure                                                                Complexity expression
  ------------------------------------------------------------------------ -----------------------------------------------------------------
  if (\[expr\]) { \[if-range\] }                                           NP(if-range) + 1 + NP(expr)
  if (\[expr\]) { \[if-range\] } else { \[else-range\] }                   NP(if-range) + NP(else-range) + NP(expr)
  while (\[expr\]) { \[while-range\] }                                     NP(while-range) + NP(expr) + 1
  do { \[do-range\] } while (\[expr\])                                     NP(do-range) + NP(expr) + 1
  for(\[expr1\]; \[expr2\]; \[expr3\]) { \[for-range\] }                   NP(for-range) + NP(expr1) + NP(expr2) + NP(expr3) + 1
  switch (\[expr\]) { case : \[case-range\] default: \[default-range\] }   S(i=1:i=n)NP(case-range\[i\]) + NP(default-range) + NP(expr)
  \[expr1\] ? \[expr2\] : \[expr3\]                                        NP(expr1) + NP(expr2) + NP(expr3) + 2
  goto label                                                               1
  break                                                                    1
  Expressions                                                              Number of && and \|\| operators in expression. No operators - 0
  continue                                                                 1
  return                                                                   1
  Statement (even sequential statements)                                   1
  Empty block {}                                                           1
  Function call                                                            1
  Function(Method) declaration or Block                                    P(i=1:i=N)NP(Statement\[i\])

  : Examples

**Rationale:** Nejmeh says that his group had an informal NPATH limit of
200 on individual routines; functions(methods) that exceeded this value
were candidates for further decomposition - or at least a closer look.
**Please do not be fanatic with limit 200** - choose number that suites
your project style. Limit 200 is empirical number base on some sources
of at AT&T Bell Laboratories of 1988 year.