Since Checkstyle 3.0

Checks for redundant modifiers in:

1. Interface and annotation definitions.
2. Final modifier on methods of final and anonymous classes.
3. Inner `interface` declarations that are declared as `static`.
4. Class constructors.
5. Nested `enum` definitions that are declared as `static`.

Rationale: The Java Language Specification strongly
discourages the usage of `public` and `abstract` for method
declarations in interface definitions as a matter of style.

Interfaces by definition are abstract so the `abstract`
modifier on the interface is redundant.

Classes inside of interfaces by definition are public and static,
so the `public` and `static` modifiers
on the inner classes are redundant. On the other hand, classes
inside of interfaces can be abstract or non abstract.
So, `abstract` modifier is allowed.

Fields in interfaces and annotations are automatically
public, static and final, so these modifiers are redundant as
well.

As annotations are a form of interface, their fields are also
automatically public, static and final just as their
annotation fields are automatically public and abstract.

Enums by definition are static implicit subclasses of java.lang.Enum\<E\>.
So, the `static` modifier on the enums is redundant. In addition,
if enum is inside of interface, `public` modifier is also redundant.

Enums can also contain abstract methods and methods which can be overridden by the
declared enumeration fields.
See the following example:


    public enum EnumClass {
      FIELD_1,
      FIELD_2 {
        @Override
        public final void method1() {} // violation expected
      };

      public void method1() {}
      public final void method2() {} // no violation expected
    }
            
Since these methods can be overridden in these situations, the final methods are not
marked as redundant even though they can't be extended by other classes/enums.

Nested `enum` types are always static by default.

Final classes by definition cannot be extended so the `final`
modifier on the method of a final class is redundant.

Public modifier for constructors in non-public non-protected classes
is always obsolete:


    public class PublicClass {
      public PublicClass() {} // OK
    }

    class PackagePrivateClass {
      public PackagePrivateClass() {} // violation expected
    }
            
There is no violation in the following example,
because removing public modifier from ProtectedInnerClass
constructor will make this code not compiling:


    package a;
    public class ClassExample {
      protected class ProtectedInnerClass {
        public ProtectedInnerClass () {}
      }
    }

    package b;
    import a.ClassExample;
    public class ClassExtending extends ClassExample {
      ProtectedInnerClass pc = new ProtectedInnerClass();
    }
            
