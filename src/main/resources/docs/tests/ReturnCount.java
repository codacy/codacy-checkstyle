//#Patterns: ReturnCount

public class Something {
    private void AAAfoo() {
        System.out.print("This private method is NOT called!");
    } // unused

    private int ____bar(int success) {
        if (success > 0) {
            return 1;
        }
        return 2;
    }

    //#Info: ReturnCount
    public int doSomething(int failure) {
        ____bar();
        if (failure > 0) {
            return 1;
        }
        if (failure < 0) {
            return 2;
        }
        return 3;
    }
}
