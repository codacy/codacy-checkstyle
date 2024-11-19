<div>

Finds nested blocks (blocks that are used freely in the code).

</div>

Rationale: Nested blocks are often leftovers from the debugging process,
they confuse the reader.

For example, this check finds the obsolete braces in

    public void guessTheOutput()
    {
      int whichIsWhich = 0;
      {
        whichIsWhich = 2;
      }
      System.out.println("value = " + whichIsWhich);
    }
            

and debugging / refactoring leftovers such as

    // if (conditionThatIsNotUsedAnyLonger)
    {
      System.out.println("unconditional");
    }
            

A case in a switch statement does not implicitly form a block. Thus, to
be able to introduce local variables that have case scope it is
necessary to open a nested block. This is supported, set the
allowInSwitchCase property to true and include all statements of the
case in the block.
