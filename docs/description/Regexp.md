Checks that a specified pattern exists, exists less than a set number of
times, or does not exist in the file.

This check combines all the functionality provided by
[RegexpHeader](https://checkstyle.org/config_header.html#RegexpHeader)
except supplying the regular expression from a file.

It differs from them in that it works in multiline mode. Its regular
expression can span multiple lines and it checks this against the whole
file at once. The others work in singleline mode. Their single or
multiple regular expressions can only span one line. They check each of
these against each line in the file in turn.

**Note:** Because of the different mode of operation there may be some
changes in the regular expressions used to achieve a particular end.

In multiline mode...

-   `^` means the beginning of a line, as opposed to beginning of the
    input.
-   For beginning of the input use `\A`.
-   `$` means the end of a line, as opposed to the end of the input.
-   For end of input use `\Z`.
-   Each line in the file is terminated with a line feed character.

**Note:** Not all regular expression engines are created equal. Some
provide extra functions that others do not and some elements of the
syntax may vary. This check makes use of the [java.util.regex
package](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/regex/package-summary.html);
please check its documentation for details of how to construct a regular
expression to achieve a particular goal.

**Note:** When entering a regular expression as a parameter in the XML
config file you must also take into account the XML rules. e.g. if you
want to match a \< symbol you need to enter &lt;. The regular expression
should be entered on one line.
