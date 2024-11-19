<div>

Checks that classes are designed for extension (subclass creation).

</div>

Nothing wrong could be with founded classes. This check makes sense only
for library projects (not application projects) which care of ideal
OOP-design to make sure that class works in all cases even misusage.
Even in library projects this check most likely will find classes that
are designed for extension by somebody. User needs to use suppressions
extensively to got a benefit from this check, and keep in suppressions
all confirmed/known classes that are deigned for inheritance
intentionally to let the check catch only new classes, and bring this to
team/user attention.

ATTENTION: Only user can decide whether a class is designed for
extension or not. The check just shows all classes which are possibly
designed for extension. If smth inappropriate is found please use
suppression.

ATTENTION: If the method which can be overridden in a subclass has a
javadoc comment (a good practice is to explain its self-use of
overridable methods) the check will not rise a violation. The violation
can also be skipped if the method which can be overridden in a subclass
has one or more annotations that are specified in ignoredAnnotations
option. Note, that by default @Override annotation is not included in
the ignoredAnnotations set as in a subclass the method which has the
annotation can also be overridden in its subclass.

Problem is described at "Effective Java, 2nd Edition by Joshua Bloch"
book, chapter "Item 17: Design and document for inheritance or else
prohibit it".

Some quotes from book:

> The class must document its self-use of overridable methods. By
> convention, a method that invokes overridable methods contains a
> description of these invocations at the end of its documentation
> comment. The description begins with the phrase “This implementation.”

> The best solution to this problem is to prohibit subclassing in
> classes that are not designed and documented to be safely subclassed.

> If a concrete class does not implement a standard interface, then you
> may inconvenience some programmers by prohibiting inheritance. If you
> feel that you must allow inheritance from such a class, one reasonable
> approach is to ensure that the class never invokes any of its
> overridable methods and to document this fact. In other words,
> eliminate the class’s self-use of overridable methods entirely. In
> doing so, you’ll create a class that is reasonably safe to subclass.
> Overriding a method will never affect the behavior of any other
> method.

The check finds classes that have overridable methods (public or
protected methods that are non-static, not-final, non-abstract) and have
non-empty implementation.

Rationale: This library design style protects superclasses against being
broken by subclasses. The downside is that subclasses are limited in
their flexibility, in particular they cannot prevent execution of code
in the superclass, but that also means that subclasses cannot corrupt
the state of the superclass by forgetting to call the superclass's
method.

More specifically, it enforces a programming style where superclasses
provide empty "hooks" that can be implemented by subclasses.

Example of code that cause violation as it is designed for extension:

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
            

Example of code without violation:

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
