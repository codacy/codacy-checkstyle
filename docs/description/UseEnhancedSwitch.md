<div>

Ensures that the enhanced switch (using `->` for case labels) is used
instead of the traditional switch (using `:` for case labels) where
possible.

</div>

Rationale: Java 14 has introduced enhancements for switch statements and
expressions that disallow fall-through behavior. The enhanced switch
syntax using `->` for case labels typically leads to more concise and
readable code, reducing the likelihood of errors associated with
fall-through cases.

See the [Java Language
Specification](https://docs.oracle.com/javase/specs/jls/se22/html/jls-14.html#jls-14.11.1-200)
for more information about `->` case labels, also known as "switch
rules".
