<div>

Detects a user-defined Javadoc block tag and reports a violation when
the tag is present with text that does not match `tagTextPattern`. With
the default pattern `^$` (same as `Regexp` format default; matches only
empty content), any non-empty text of the configured tag is a violation.
No violation is reported when there is no Javadoc or when `tag` is not
configured.

</div>
