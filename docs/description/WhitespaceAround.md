Checks that a token is surrounded by whitespace. Empty constructor,
method, class, enum, interface, loop bodies (blocks), lambdas of the
form

    public MyClass() {}      // empty constructor
    public void func() {}    // empty method
    public interface Foo {} // empty interface
    public class Foo {} // empty class
    public enum Foo {} // empty enum
    MyClass c = new MyClass() {}; // empty anonymous class
    while (i = 1) {} // empty while loop
    for (int i = 1; i > 1; i++) {} // empty for loop
    do {} while (i = 1); // empty do-while loop
    Runnable noop = () -> {}; // empty lambda
    public @interface Beta {} // empty annotation type
            

may optionally be exempted from the policy using the
`           allowEmptyMethods`, `allowEmptyConstructors`,
`allowEmptyTypes`, `allowEmptyLoops`, `allowEmptyLambdas` and
`allowEmptyCatches` properties.

This check does not flag as violation double brace initialization like:

<div class="wrapper">

    new Properties() {{
        setProperty("key", "value");
    }};
              

</div>

Parameter allowEmptyCatches allows to suppress violations when token
list contains SLIST to check if beginning of block is surrounded by
whitespace and catch block is empty, for example:

<div class="wrapper">

    try {
        k = 5 / i;
    } catch (ArithmeticException ex) {}
              

</div>

With this property turned off, this raises violation because the
beginning of the catch block (left curly bracket) is not separated from
the end of the catch block (right curly bracket).
