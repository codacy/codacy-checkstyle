//#Patterns: TodoComment

public class Something {
    private void AAAfoo() {
        System.out.print("This private method is NOT called!");
    } // unused

    private void ____bar() {
        // FIXME: use logger instead of System.out
        System.out.print("This private method is called!");
    }

           public void doSomething() {
               //#Info: TodoComment
               bar();  // TODO: implementation
    }

           public void doSomething() {
        bar();
    }
}
