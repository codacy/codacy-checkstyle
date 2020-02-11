Checks correct indentation of Java code.

The idea behind this is that while pretty printers are sometimes
convenient for bulk reformats of legacy code, they often either aren\'t
configurable enough or just can\'t anticipate how format should be done.
Sometimes this is personal preference, other times it is practical
experience. In any case, this check should just ensure that a minimal
set of indentation rules is followed.

Basic offset indentation is used for indentation inside code blocks. For
any lines that span more than 1, line wrapping indentation is used for
those lines after the first. Brace adjustment, case, and throws
indentations are all used only if those specific identifiers start the
line. If, for example, a brace is used in the middle of the line, its
indentation will not take effect. All indentations have an
accumulative/recursive effect when they are triggered. If during a line
wrapping, another code block is found and it doesn\'t end on that same
line, then the subsequent lines afterwards, in that new code block, are
increased on top of the line wrap and any indentations above it.

Example:

    if ((condition1 && condition2)
            || (condition3 && condition4)    // line wrap with bigger indentation
            ||!(condition5 && condition6)) { // line wrap with bigger indentation
      field.doSomething()                    // basic offset
          .doSomething()                     // line wrap
          .doSomething( c -> {               // line wrap
            return c.doSome();               // basic offset
          });
    }