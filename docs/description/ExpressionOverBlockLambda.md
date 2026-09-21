<div>

Checks that expression lambdas are used instead of single-line block
lambdas where possible.

</div>

Rationale: According to the OpenJDK Java Style Guidelines (and general
modern Java conventions), expression lambdas are preferred over
single-line block lambdas for readability and conciseness.

A single-line block lambda is a lambda whose body is a block (`{...}`)
that fits on a single line and contains only one statement that could be
written as an expression lambda.
