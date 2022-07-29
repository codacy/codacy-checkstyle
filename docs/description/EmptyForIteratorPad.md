Checks the padding of an empty for iterator; that is whether a white
space is required at an empty for iterator, or such white space is
forbidden. No check occurs if there is a line wrap at the iterator, as
in

    for (Iterator foo = very.long.line.iterator();
          foo.hasNext();
         )
