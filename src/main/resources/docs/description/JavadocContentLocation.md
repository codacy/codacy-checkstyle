Checks that the Javadoc content begins from the same position for all
Javadoc comments in the project. Any leading asterisks and spaces are
not counted as the beginning of the content and are therefore ignored.

It is possible to enforce two different styles:

-   {\@code first\_line} - Javadoc content starts from the first line:

        /** Summary text.
          * More details.
          */
        public void method();
                    

-   {\@code second\_line} - Javadoc content starts from the second line:

        /**
          * Summary text.
          * More details.
          */
        public void method();