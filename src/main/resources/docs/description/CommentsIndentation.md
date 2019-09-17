Controls the indentation between comments and surrounding code. Comments
are indented at the same level as the surrounding code. Detailed info
about such convention can be found
[here](styleguides/google-java-style-20180523/javaguide.html#s4.8.6.1-block-comment-style)

Please take a look at the following examples to understand how the check
works:

Example \#1: Block comments.

    1   /*
    2    * it is Ok
    3    */
    4   boolean bool = true;
    5
    6     /* violation
    7      * (block comment should have the same indentation level as line 9)
    8      */
    9   double d = 3.14;
            

Example \#2: Comment is placed at the end of the block and has previous
statement.

    1   public void foo1() {
    2     foo2();
    3     // it is OK
    4   }
    5
    6   public void foo2() {
    7     foo3();
    8       // violation (comment should have the same indentation level as line 7)
    9   }
            

Example \#3: Comment is used as a single line border to separate groups
of methods.

    1   /////////////////////////////// it is OK
    2
    3   public void foo7() {
    4     int a = 0;
    5   }
    6
    7     ///////////////////////////// violation (should have the same indentation level as line 9)
    8
    9   public void foo8() {}
            

Example \#4: Comment has distributed previous statement.

    1   public void foo11() {
    2     CheckUtil
    3       .getFirstNode(new DetailAST())
    4       .getFirstChild()
    5       .getNextSibling();
    6     // it is OK
    7   }
    8
    9   public void foo12() {
    10    CheckUtil
    11      .getFirstNode(new DetailAST())
    12      .getFirstChild()
    13      .getNextSibling();
    14              // violation (should have the same indentation level as line 10)
    15  }
            

Example \#5: Single line block comment is placed within an empty code
block. Note, if comment is placed at the end of the empty code block, we
have Checkstyle\'s limitations to clearly detect user intention of
explanation target - above or below. The only case we can assume as a
violation is when a single line comment within the empty code block has
indentation level that is lower than the indentation level of the
closing right curly brace.

    1   public void foo46() {
    2     // comment
    3     // block
    4     // it is OK (we cannot clearly detect user intention of explanation target)
    5   }
    6
    7   public void foo46() {
    8  // comment
    9  // block
    10 // violation (comment should have the same indentation level as line 11)
    11  }
            

Example \#6: \'fallthrough\' comments and similar.

    0   switch(a) {
    1     case "1":
    2       int k = 7;
    3       // it is OK
    4     case "2":
    5       int k = 7;
    6     // it is OK
    7     case "3":
    8       if (true) {}
    9           // violation (should have the same indentation level as line 8 or 10)
    10    case "4":
    11    case "5": {
    12      int a;
    13    }
    14    // fall through (it is OK)
    15    case "12": {
    16      int a;
    17    }
    18    default:
    19      // it is OK
    20  }
            

Example \#7: Comment is placed within a distributed statement.

    1   String breaks = "J"
    2   // violation (comment should have the same indentation level as line 3)
    3       + "A"
    4       // it is OK
    5       + "V"
    6       + "A"
    7   // it is OK
    8   ;
            

Example \#8: Comment is placed within an empty case block. Note, if
comment is placed at the end of the empty case block, we have
Checkstyle\'s limitations to clearly detect user intention of
explanation target - above or below. The only case we can assume as a
violation is when a single line comment within the empty case block has
indentation level that is lower than the indentation level of the next
case token.

    1   case 4:
    2     // it is OK
    3   case 5:
    4  // violation (should have the same indentation level as line 3 or 5)
    5   case 6:
            

Example \#9: Single line block comment has previous and next statement.

    1   String s1 = "Clean code!";
    2      s.toString().toString().toString();
    3   // single line
    4   // block
    5   // comment (it is OK)
    6   int a = 5;
    7
    8   String s2 = "Code complete!";
    9    s.toString().toString().toString();
    10            // violation (should have the same indentation level as line 11)
    11       // violation (should have the same indentation level as line 12)
    12     // violation (should have the same indentation level as line 13)
    13  int b = 18;
            

Example \#10: Comment within the block tries to describe the next code
block.

    1   public void foo42() {
    2     int a = 5;
    3     if (a == 5) {
    4       int b;
    5       // it is OK
    6      } else if (a ==6) { ... }
    7   }
    8
    9   public void foo43() {
    10    try {
    11      int a;
    12     // Why do we catch exception here? - violation (not the same indentation as line 11)
    13     } catch (Exception e) { ... }
    14  }
            
