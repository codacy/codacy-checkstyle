//#Patterns: SeparatorWrap: {"option": "nl"}

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

    public void doSomething() {
        System
            .out
            .print("That works!");
    }

           public void doSomething() {
        bar();
    }
}
