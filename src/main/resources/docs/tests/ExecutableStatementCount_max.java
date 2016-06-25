//#Patterns: ExecutableStatementCount: {"max": 5}

public class Something {
    private void AAAfoo() {
        System.out.print("This private method is NOT called!");
    } // unused

    //#Info: ExecutableStatementCount
    public Something() {
        Integer i0 = new Integer(1); Integer i1 = new Integer(1); Integer i2 = new Integer(1);
        Integer i3 = new Integer(1); Integer i4 = new Integer(1); Integer i5 = new Integer(1);
        Integer i6 = new Integer(1);
    }

    //#Info: ExecutableStatementCount
    private void ____bar() {
        System.out.println("3 bottles of beer on the wall, 3 bottles of beer.");
        System.out.println("Take one down and pass it around, 2 bottles of beer on the wall.");
        System.out.println("2 bottles of beer on the wall, 2 bottles of beer.");
        System.out.println("Take one down and pass it around, 1 bottle of beer on the wall.");
        System.out.println("1 bottle of beer on the wall, 1 bottle of beer.");
        System.out.println("Take one down and pass it around, no more bottles of beer on the wall.");
    }

    private void ____bar1() {
        System.out.println("No more bottles of beer on the wall, no more bottles of beer.");
    }

    private void ____bar2() {
        System.out.println("Go to the store and buy some more, 3 bottles of beer on the wall.");
    }

    private void ____bar3() {
        ____bar();
    }

    public void doSomething() {
        ____bar();
        ____bar1();
        ____bar2();
        ____bar3();
        doSomething();
    }
}
