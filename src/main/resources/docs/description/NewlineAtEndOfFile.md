Checks whether files end with a line separator.

Rationale: Any source files and text files in general should end with a
line separator to let other easily add new content at the end of file
and \"diff\" command does not show previous lines as changed.

Example (line 36 should not be in diff):

    @@ -32,4 +32,5 @@ ForbidWildcardAsReturnTypeCheck.returnTypeClassNamesIgnoreRegex
    PublicReferenceToPrivateTypeCheck.name = Public Reference To Private Type

    StaticMethodCandidateCheck.name = Static Method Candidate
    -StaticMethodCandidateCheck.desc = Checks whether private methods should be declared as static.
    \ No newline at end of file
    +StaticMethodCandidateCheck.desc = Checks whether private methods should be declared as static.
    +StaticMethodCandidateCheck.skippedMethods = Method names to skip during the check.
            

It can also trick the VCS to report the wrong owner for such lines. An
engineer who has added nothing but a newline character becomes the last
known author for the entire line. As a result, a mate can ask him a
question to which he will not give the correct answer.

Old Rationale: CVS source control management systems will even print a
warning when it encounters a file that doesn\'t end with a line
separator.

Attention: property fileExtensions works with files that are passed by
similar property for at
[Checker](https://checkstyle.org/config.html#Checker). Please make sure
required file extensions are mentioned at Checker\'s fileExtensions
property.