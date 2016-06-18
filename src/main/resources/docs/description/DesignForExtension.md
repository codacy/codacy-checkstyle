# Description

The Check finds classes that are designed for extension (subclass creation).

Nothing wrong could be with founded classes this Check make sence only for
library project (not a application projects) who care about ideal OOP design to
make sure class work in all cases even misusage. Even in library projects this
Check most likely finds classes that are not required to check. User need to
use suppressions extensively to got a benefit from this Check and avoid false
positives.

ATTENTION: Only user can deside whether class is designed for extension or not.
Check just show all possible. If smth inappropriate is found please use
supporession.

Problem is described at "Effective Java, 2nd Edition by Josh Bloch" book,
chapter "Item 17: Design and document for inheritance or else prohibit it".

Some quotes from book:

The class must document its self-use of overridable methods. By convention, a
method that invokes overridable methods contains a description of these
invocations at the end of its documentation comment. The description begins
with the phrase “This implementation.”
The best solution to this problem is to prohibit subclassing in classes that
are not designed and documented to be safely subclassed.
If a concrete class does not implement a standard interface, then you may
inconvenience some programmers by prohibiting inheritance. If you feel that you
must allow inheritance from such a class, one reasonable approach is to ensure
that the class never invokes any of its overridable methods and to document
this fact. In other words, eliminate the class’s self-use of overridable
methods entirely. In doing so, you’ll create a class that is reasonably safe
to subclass. Overriding a method will never affect the behavior of any other
method.

The exact rule is that non-private, non-static methods of classes that can be
subclassed must

* be abstract or
* be final or
* have an empty implementation.

Rationale: This library design style protects superclasses against being broken
by subclasses. The downside is that subclasses are limited in their flexibility,
in particular they cannot prevent execution of code in the superclass, but that
also means that subclasses cannot corrupt the state of the superclass by
forgetting to call the superclass's method.

More specifically, it enforces a programming style where superclasses provide
empty "hooks" that can be implemented by subclasses.

# Examples

Example of code that cause violation as it is designed for extension:

```
public abstract class Plant {
    private String roots;
    private String trunk;

    protected void validate() {
      if (roots == null) throw new IllegalArgumentException("No roots!");
      if (trunk == null) throw new IllegalArgumentException("No trunk!");
    }

    public abstract void grow();
}

public class Tree extends Plant {
    private List leaves;

    @Overrides
    protected void validate() {
      super.validate();
      if (leaves == null) throw new IllegalArgumentException("No leaves!");
    }

    public void grow() {
      validate();
    }
}
```

Example of code without violation:

```
public abstract class Plant {
    private String roots;
    private String trunk;

    private void validate() {
        if (roots == null) throw new IllegalArgumentException("No roots!");
        if (trunk == null) throw new IllegalArgumentException("No trunk!");
        validateEx();
    }

    protected void validateEx() { }

    public abstract void grow();
}
```
