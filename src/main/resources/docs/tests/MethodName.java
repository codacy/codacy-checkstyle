//#Patterns: MethodName

public class Something {
    //#Info: MethodName
    private void AAAfoo() {
        System.out.print("This private method is NOT called!");
    } // unused

    //#Info: MethodName
    private void ____bar() {
        System.out.print("This private method is called!");
    }

    public void doSomething() {
        bar();
    }

           public void doSomething() {
                         bar();
     }
}