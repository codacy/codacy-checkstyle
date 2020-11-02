//#Patterns: OperatorWrap

public class Something {
    private void AAAfoo() {
        System.out.print("This private method is NOT "
            + "called!");
    } // unused

    private int ____bar() {
        System.out.print("There is nothing fishy "
                         + "about this method");
        return 42
          * 3;
    }

    public float doSomething() {
        //#Info: OperatorWrap
        System.out.println("Hello" +
                           "world!");
        //#Info: OperatorWrap
        return (5.42 *
        //#Info: OperatorWrap
                7.14) / (25 * 13) *
               4;
    }
}
