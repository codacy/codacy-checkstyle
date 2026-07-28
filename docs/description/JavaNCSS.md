<div>

Determines complexity of methods, classes and files by counting the Non
Commenting Source Statements (NCSS). This check adheres to the
[specification](http://www.kclee.de/clemens/java/javancss/#specification)
for the [JavaNCSS-Tool](http://www.kclee.de/clemens/java/javancss/)
written by **Chr. Clemens Lee**.

</div>

Roughly said the NCSS metric is calculated by counting the source lines
which are not comments, (nearly) equivalent to counting the semicolons
and opening curly braces.

The NCSS for a class is summarized from the NCSS of all its methods, the
NCSS of its nested classes and the number of member variable
declarations.

The NCSS for a file is summarized from the ncss of all its top level
classes, the number of imports and the package declaration.

Rationale: Too large methods and classes are hard to read and costly to
maintain. A large NCSS number often means that a method or class has too
many responsibilities and/or functionalities which should be decomposed
into smaller units.

Here is a breakdown of what exactly is counted and not counted:

<div class="wrapper">

<table>
<caption>JavaNCSS metrics</caption>
<thead>
<tr class="header">
<th>Structure</th>
<th>NCSS Count</th>
<th>Notes</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>Package declaration</td>
<td>1</td>
<td>Counted at the terminating semicolon.</td>
</tr>
<tr class="even">
<td>Import declaration</td>
<td>1</td>
<td>Each single, static, or wildcard import counts as 1.</td>
</tr>
<tr class="odd">
<td>Class, Interface, Annotation (<code>@interface</code>)</td>
<td>1</td>
<td>Counted at the opening curly brace of the body.</td>
</tr>
<tr class="even">
<td>Method, Constructor</td>
<td>1</td>
<td>Counted at the declaration.</td>
</tr>
<tr class="odd">
<td>Static initializer, Instance initializer</td>
<td>1</td>
<td>Both <code>static {}</code> and bare <code>{}</code> initializer
blocks count as 1.</td>
</tr>
<tr class="even">
<td>Annotation type member</td>
<td>1</td>
<td>Each method-like member declaration inside <code>@interface</code>
counts as 1. A standalone <code>;</code> inside <code>@interface</code>
also counts as 1.</td>
</tr>
<tr class="odd">
<td>Variable declaration</td>
<td>1</td>
<td>1 per statement regardless of how many variables are declared on
that line. <code>int x, y;</code> counts as 1.</td>
</tr>
<tr class="even">
<td><code>if</code></td>
<td>1</td>
<td>The <code>if</code> keyword counts as 1.</td>
</tr>
<tr class="odd">
<td><code>else</code>, <code>else if</code></td>
<td>1</td>
<td>The <code>else</code> keyword counts as 1, separate from the
<code>if</code> count.</td>
</tr>
<tr class="even">
<td><code>while</code>, <code>do</code>, <code>for</code></td>
<td>1</td>
<td>The keyword header counts as 1.</td>
</tr>
<tr class="odd">
<td><code>switch</code></td>
<td>1</td>
<td>The <code>switch</code> keyword counts as 1.</td>
</tr>
<tr class="even">
<td><code>case</code>, <code>default</code></td>
<td>1</td>
<td>Every case and default label adds 1.</td>
</tr>
<tr class="odd">
<td><code>try</code></td>
<td>0</td>
<td>The <code>try</code> keyword itself does not count.</td>
</tr>
<tr class="even">
<td><code>catch</code></td>
<td>1</td>
<td>Each catch block counts as 1.</td>
</tr>
<tr class="odd">
<td><code>finally</code></td>
<td>1</td>
<td>The finally block counts as 1.</td>
</tr>
<tr class="even">
<td><code>synchronized</code></td>
<td>1</td>
<td>The synchronized statement counts as 1.</td>
</tr>
<tr class="odd">
<td><code>return</code>, <code>break</code>, <code>continue</code>,
<code>throw</code></td>
<td>1</td>
<td>Each counts as 1.</td>
</tr>
<tr class="even">
<td><code>assert</code></td>
<td>1</td>
<td>Each assert statement counts as 1, with or without a message
expression.</td>
</tr>
<tr class="odd">
<td>Labeled statement</td>
<td>1</td>
<td><code>label: statement</code> counts as 1.</td>
</tr>
<tr class="even">
<td>Explicit constructor invocation</td>
<td>1</td>
<td><code>this()</code> or <code>super()</code> calls inside a
constructor body each count as 1.</td>
</tr>
<tr class="odd">
<td>Expression statements (assignments, method calls)</td>
<td>1</td>
<td>Statement-level expressions terminated by <code>;</code> count as 1.
A method call inside a <code>return</code> does not add an extra
count.</td>
</tr>
<tr class="even">
<td>Empty blocks {}</td>
<td>0</td>
<td>Empty curly braces do not increase the count.</td>
</tr>
<tr class="odd">
<td>Empty statements ;</td>
<td>0</td>
<td>Standalone semicolons outside of <code>@interface</code> do not
increase the count.</td>
</tr>
</tbody>
</table>

JavaNCSS metrics

</div>
