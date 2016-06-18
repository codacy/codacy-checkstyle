//#Patterns: TodoComment: {"format": "(TODO|FIXME):"}

public class Something {
    private void AAAfoo() {
        System.out.print("This private method is NOT called!");
    } // unused

    private void ____bar() {
        System.out.print("This private method is called!");
    }

           public void doSomething() {
               //#Info: TodoComment
               bar();  // TODO: implementation
               //#Info: TodoComment
               foo();  // FIXME: this may not work with external requests
    }

           public void doSomething() {
        bar();
    }
}
