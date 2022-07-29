Detects if keys in properties files are in correct order.

Rationale: Sorted properties make it easy for people to find required
properties by name in file. This makes it easier to merge. While there
are no problems at runtime. This check is valuable only on files with
string resources where order of lines does not matter at all, but this
can be improved. E.g.:
checkstyle/src/main/resources/com/puppycrawl/tools/checkstyle/messages.properties
You may suppress warnings of this check for files that have a logical
structure like build files or log4j configuration files. See
SuppressionFilter.
`               <suppress checks="OrderedProperties"                   files="log4j.properties|ResourceBundle/Bug.*.properties|logging.properties"/>           `

Known limitation: The key should not contain a newline. The string
compare will work, but not the line number reporting.
