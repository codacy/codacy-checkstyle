<div>

Checks that the Javadoc content begins from the same position for all
Javadoc comments in the project. Any leading asterisks and spaces are
not counted as the beginning of the content and are therefore ignored.

</div>

It is possible to enforce two different styles:

- {@code first_line} - Javadoc content starts from the first line:

      /** Summary text.
        * More details.
        */
      public void method();
                  

- {@code second_line} - Javadoc content starts from the second line:

      /**
        * Summary text.
        * More details.
        */
      public void method();
