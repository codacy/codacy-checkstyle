Since Checkstyle 3.1

Checks whether files end with a line separator.

Rationale: Any source files and text files in general should
end with a line separator to let other easily add new content
at the end of file and "diff" command does not show previous lines as changed.

Example (line 36 should not be in diff):
![example of diff](https://cloud.githubusercontent.com/assets/812984/13894408/afd965b8-ed24-11e5-8bfd-e9edf56a6fe6.png)

Old Rationale: CVS source control management
systems will even print a warning when it
encounters a file that doesn't end with a line separator.

Attention: property fileExtensions works with files that are passed by similar property
for at [Checker](config.html#Checker).
Please make sure required file extensions are mentioned at Checker's fileExtensions
property.
