Checks that the clone method is not overridden from the Object class.

This check is almost exactly the same as the `NoFinalizerCheck`.

See
[Object.clone()](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Object.html#clone\(\))

Rationale: The clone method relies on strange, hard to follow rules that
are difficult to get right and do not work in all situations. In some
cases, either a copy constructor or a static factory method can be used
instead of the clone method to return copies of an object. For more
information on rules for the clone method and its issues, see Effective
Java: Programming Language Guide First Edition by Joshua Bloch pages
45-52.

Below are some of the rules/reasons why the clone method should be
avoided.

  - Classes supporting the clone method should implement the Cloneable
    interface but the Cloneable interface does not include the clone
    method. As a result, it doesn't enforce the method override.
  - The Cloneable interface forces the Object's clone method to work
    correctly. Without implementing it, the Object's clone method will
    throw a CloneNotSupportedException.
  - Non-final classes must return the object returned from a call to
    super.clone().
  - Final classes can use a constructor to create a clone which is
    different from non-final classes.
  - If a super class implements the clone method incorrectly all
    subclasses calling super.clone() are doomed to failure.
  - If a class has references to mutable objects then those object
    references must be replaced with copies in the clone method after
    calling super.clone().
  - The clone method does not work correctly with final mutable object
    references because final references cannot be reassigned.
  - If a super class overrides the clone method then all subclasses must
    provide a correct clone implementation.

Two alternatives to the clone method, in some cases, is a copy
constructor or a static factory method to return copies of an object.
Both of these approaches are simpler and do not conflict with final
fields. They do not force the calling client to handle a
CloneNotSupportedException. They also are typed therefore no casting is
necessary. Finally, they are more flexible since they can take interface
types rather than concrete classes.

Sometimes a copy constructor or static factory is not an acceptable
alternative to the clone method. The example below highlights the
limitation of a copy constructor (or static factory). Assume Square is a
subclass for Shape.

``` 
Shape s1 = new Square();
System.out.println(s1 instanceof Square); //true
        
```

...assume at this point the code knows nothing of s1 being a Square
that's the beauty of polymorphism but the code wants to copy the Square
which is declared as a Shape, its super type...

``` 
Shape s2 = new Shape(s1); //using the copy constructor
System.out.println(s2 instanceof Square); //false
        
```

The working solution (without knowing about all subclasses and doing
many casts) is to do the following (assuming correct clone
implementation).

``` 
Shape s2 = s1.clone();
System.out.println(s2 instanceof Square); //true
        
```

Just keep in mind if this type of polymorphic cloning is required then a
properly implemented clone method may be the best choice.

Much of this information was taken from Effective Java: Programming
Language Guide First Edition by Joshua Bloch pages 45-52. Give Bloch
credit for writing an excellent book.
