Checks for fall-through in `switch` statements. Finds locations where a
`case` **contains** Java code but lacks a `break`, `return`, `throw` or
`continue` statement.

The check honors special comments to suppress the warning. By default
the texts \"fallthru\", \"fall thru\", \"fall-thru\", \"fallthrough\",
\"fall through\", \"fall-through\" \"fallsthrough\", \"falls through\",
\"falls-through\" (case sensitive). The comment containing these words
must be all on one line, and must be on the last non-empty line before
the `case` triggering the warning or on the same line before the `case`
(ugly, but possible).

    switch (i) {
    case 0:
      i++; // fall through

    case 1:
      i++;
      // falls through
    case 2:
    case 3:
    case 4: {
      i++;
    }
    // fallthrough
    case 5:
      i++;
    /* fallthru */case 6:
      i++;
    // fall-through
    case 7:
      i++;
      break;
    }
            

Note: The check assumes that there is no unreachable code in the `case`.

The following fragment of code will NOT trigger the check, because of
the comment \"fallthru\" or any Java code in case 5 are absent.

::: {.wrapper}
    case 3:
        x = 2;
        // fallthru
    case 4:
    case 5: // violation
    case 6:
        break;
              
:::