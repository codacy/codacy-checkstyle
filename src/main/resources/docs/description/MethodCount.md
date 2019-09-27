Since Checkstyle 5.3

Checks the number of methods declared in each type declaration by access
modifier or total count.

This check can be configured to flag classes that define too many
methods to prevent the class from getting too complex. Counting can be
customized to prevent too many total methods in a type definition
(`maxTotal`), or to prevent too many methods of a specific access
modifier (`private`, `package`, `protected` or `public`). Each count is
completely separated to customize how many methods of each you want to
allow. For example, specifying a `maxTotal` of 10, still means you can
prevent more than 0 `maxPackage` methods. A violation won\'t appear for
8 public methods, but one will appear if there is also 3 private methods
or any package-private methods.

Methods defined in anonymous classes are not counted towards any totals.
Counts only go towards the main type declaration parent, and are kept
separate from it\'s children\'s inner types.

    public class ExampleClass {
      public enum Colors {
        RED, GREEN, YELLOW;

        public String getRGB() { ... } // NOT counted towards ExampleClass
      }

      public void example() { // counted towards ExampleClass
        Runnable r = (new Runnable() {
          public void run() { ... } // NOT counted towards ExampleClass and won't produce any violations
        });
      }

      public static class InnerExampleClass {
        protected void example2() { ... } // NOT counted towards ExampleClass,
                                       // but counted towards InnerExampleClass
      }
    }