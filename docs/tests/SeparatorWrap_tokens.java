//#Patterns: SeparatorWrap: {"option": "nl", "tokens": "DOT"}

public class Something {
    private void AAAfoo() {
        System.out.print("This private method is NOT called!");
    } // unused

    private void ____bar() {
        //#Info: SeparatorWrap
        System.
            //#Info: SeparatorWrap
            out.
            print("This private method is called!");
    }

    private int[] mData = {
        1,
        2,
        3,
        4
    };

    public void doSomething() {
        System
            .out
            .print("That works!");
    }

           public void doSomething() {
        bar();
    }
}
