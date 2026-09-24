<div>

Requires user defined Javadoc tag to be present in Javadoc comment with
defined format. To define the format for a tag, set property tagFormat
to a regular expression. Violations are reported only when the
configured tag is missing or when the tag content does not match
tagFormat. No violation is reported when the tag is present and matches
tagFormat (or when tagFormat is not configured). No violation reported
in case there is no javadoc. To forbid tags instead of requiring them,
use IllegalBlockTag.

</div>
