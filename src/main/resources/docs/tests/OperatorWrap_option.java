//#Patterns: OperatorWrap: {"option": "eol"}

public class Something {
    private void AAAfoo() {
        System.out.print("This private method is NOT "
        //#Info: OperatorWrap
            + "called!");
    } // unused

    private int ____bar() {
        System.out.print("There is nothing fishy "
        //#Info: OperatorWrap
                         + "about this method");
        return 42
        //#Info: OperatorWrap
          * 3;
    }

    public float doSomething() {
        System.out.println("Hello" +
                           "world!");
        return (5.42 *
                7.14) / (25 * 13) *
               4;
    }
}
