Checks that the whitespace around the Generic tokens (angle brackets)
\"\<\" and \"\>\" are correct to the *typical* convention. The
convention is not configurable.

Left angle bracket (\"\<\"):

-   should be preceded with whitespace only in generic methods
    definitions.
-   should not be preceded with whitespace when it is precede method
    name or following type name.
-   should not be followed with whitespace in all cases.

Right angle bracket (\"\>\"):

-   should not be preceded with whitespace in all cases.
-   should be followed with whitespace in almost all cases, except
    diamond operators and when preceding method name.

Examples with correct spacing:

    // Generic methods definitions
    public void <K, V extends Number> boolean foo(K, V) {}
    // Generic type definition
    class name<T1, T2, ..., Tn> {}
    // Generic type reference
    OrderedPair<String, Box<Integer>> p;
    // Generic preceded method name
    boolean same = Util.<Integer, String>compare(p1, p2);
    // Diamond operator
    Pair<Integer, String> p1 = new Pair<>(1, "apple");
    // Method reference
    List<T> list = ImmutableList.Builder<T>::new;
    // Method reference
    sort(list, Comparable::<String>compareTo);
            
