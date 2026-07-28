<div>

Checks that the Javadoc content begins from the same position for all
Javadoc comments in the project. Any leading asterisks and spaces are
not counted as the beginning of the content and are therefore ignored.

</div>

It is possible to enforce two different styles:

- `first_line` - Javadoc content starts from the first line:
  <div class="wrapper">

  ``` prettyprint
  /** Summary text.
    * More details.
    */
  public void method();
          
  ```

  </div>
- `second_line` - Javadoc content starts from the second line:
  <div class="wrapper">

  ``` prettyprint
  /**
    * Summary text.
    * More details.
    */
  public void method();
          
  ```

  </div>
