//#Patterns: SeparatorWrap

public class Something {
    private void AAAfoo() {
        System.out.print("This private method is NOT called!");
    } // unused

    private void ____bar() {
        System.
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
            //#Info: SeparatorWrap
            .out
            //#Info: SeparatorWrap
            .print("That works");
    }

           public void doSomething() {
        bar();
    }
}
