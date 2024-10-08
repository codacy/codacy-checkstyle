Checks the NPATH complexity against a specified limit.

The NPATH metric computes the number of possible execution paths through
a function(method). It takes into account the nesting of conditional
statements and multipart boolean expressions (A && B, C || D, E ? F :G
and their combinations).

The NPATH metric was designed base on Cyclomatic complexity to avoid
problem of Cyclomatic complexity metric like nesting level within a
function(method).

Metric was described at ["NPATH: a measure of execution pathcomplexity
and its applications"](http://dl.acm.org/citation.cfm?id=42379). If you
need detailed description of algorithm, please read that article, it is
well written and have number of examples and details.

Here is some quotes:

> An NPATH threshold value of 200 has been established for a function.
> The value 200 is based on studies done at AT&T Bell Laboratories
> \[1988 year\].

> Some of the most effective methods of reducing the NPATH value
> include:
>
> - distributing functionality;
> - implementing multiple if statements as a switch statement;
> - creating a separate function for logical expressions with a high
>   count of variables and (&&) and or (||) operators.

> Although strategies to reduce the NPATH complexity of functions are
> important, care must be taken not to distort the logical clarity of
> the software by applying a strategy to reduce the complexity of
> functions. That is, there is a point of diminishing return beyond
> which a further attempt at reduction of complexity distorts the
> logical clarity of the system structure.

<div class="wrapper">

<table>
<caption>Examples</caption>
<thead>
<tr class="header">
<th>Structure</th>
<th>Complexity expression</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>if ([expr]) { [if-range] }</td>
<td>NP(if-range) + 1 + NP(expr)</td>
</tr>
<tr class="even">
<td>if ([expr]) { [if-range] } else { [else-range] }</td>
<td>NP(if-range) + NP(else-range) + NP(expr)</td>
</tr>
<tr class="odd">
<td>while ([expr]) { [while-range] }</td>
<td>NP(while-range) + NP(expr) + 1</td>
</tr>
<tr class="even">
<td>do { [do-range] } while ([expr])</td>
<td>NP(do-range) + NP(expr) + 1</td>
</tr>
<tr class="odd">
<td>for([expr1]; [expr2]; [expr3]) { [for-range] }</td>
<td>NP(for-range) + NP(expr1) + NP(expr2) + NP(expr3) + 1</td>
</tr>
<tr class="even">
<td>switch ([expr]) { case : [case-range] default: [default-range]
}</td>
<td>S(i=1:i=n)NP(case-range[i]) + NP(default-range) + NP(expr)</td>
</tr>
<tr class="odd">
<td>when[expr]</td>
<td>NP(expr) + 1</td>
</tr>
<tr class="even">
<td>[expr1] ? [expr2] : [expr3]</td>
<td>NP(expr1) + NP(expr2) + NP(expr3) + 2</td>
</tr>
<tr class="odd">
<td>goto label</td>
<td>1</td>
</tr>
<tr class="even">
<td>break</td>
<td>1</td>
</tr>
<tr class="odd">
<td>Expressions</td>
<td>Number of &amp;&amp; and || operators in expression. No operators -
0</td>
</tr>
<tr class="even">
<td>continue</td>
<td>1</td>
</tr>
<tr class="odd">
<td>return</td>
<td>1</td>
</tr>
<tr class="even">
<td>Statement (even sequential statements)</td>
<td>1</td>
</tr>
<tr class="odd">
<td>Empty block {}</td>
<td>1</td>
</tr>
<tr class="even">
<td>Function call</td>
<td>1</td>
</tr>
<tr class="odd">
<td>Function(Method) declaration or Block</td>
<td>P(i=1:i=N)NP(Statement[i])</td>
</tr>
</tbody>
</table>

Examples

</div>

**Rationale:** Nejmeh says that his group had an informal NPATH limit of
200 on individual routines; functions(methods) that exceeded this value
were candidates for further decomposition - or at least a closer look.
**Please do not be fanatic with limit 200** - choose number that suites
your project style. Limit 200 is empirical number base on some sources
of at AT&T Bell Laboratories of 1988 year.
