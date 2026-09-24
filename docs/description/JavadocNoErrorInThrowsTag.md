<div>

Checks that `Error` types are not documented in `@throws` or
`@exception` Javadoc tags.

</div>

Per the documentation comments style guide, errors generally should not
be documented because they are unpredictable. This check reports
documented throwable names whose simple name ends with `Error`, unless
the same Error type is explicitly thrown with `throw new` in the
documented method or constructor.
