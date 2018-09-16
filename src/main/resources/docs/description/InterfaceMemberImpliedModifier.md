Checks for implicit modifiers on interface members and nested types.

This check is effectively the opposite of RedundantModifier. It checks the modifiers on interface members, ensuring that certain modifiers are explicitly specified even though they are actually redundant.

Methods in interfaces are `public` by default, however from Java 9 they can also be `private`. This check provides the ability to enforce that `public` is explicitly coded and not implicitly added by the compiler.

From Java 8, there are three types of methods in interfaces - static methods marked with `static`, default methods marked with `default` and abstract methods which do not have to be marked with anything. From Java 9, there are also private methods marked with `private`. This check provides the ability to enforce that `abstract` is explicitly coded and not implicitly added by the compiler.

Fields in interfaces are always `public static final` and as such the compiler does not require these modifiers. This check provides the ability to enforce that these modifiers are explicitly coded and not implicitly added by the compiler.

Nested types within an interface are always `public static` and as such the compiler does not require the `public static` modifiers. This check provides the ability to enforce that the `public` and `static` modifiers are explicitly coded and not implicitly added by the compiler.

    public interface AddressFactory {
      // check enforces code contains "public static final"
      public static final String UNKNOWN = "Unknown";
    
      String OTHER = "Other";  // violation
    
      // check enforces code contains "public" or "private"
      public static AddressFactory instance();
    
      // check enforces code contains "public abstract"
      public abstract Address createAddress(String addressLine, String city);
    
      List<Address> findAddresses(String city);  // violation
    
      // check enforces default methods are explicitly declared "public"
      public default Address createAddress(String city) {
        return createAddress(UNKNOWN, city);
      }
    
      default Address createOtherAddress() {  // violation
        return createAddress(OTHER, OTHER);
      }
    }

Rationale for this check: Methods, fields and nested types are treated differently depending on whether they are part of an interface or part of a class. For example, by default methods are package-scoped on classes, but public in interfaces. However, from Java 8 onwards, interfaces have changed to be much more like abstract classes. Interfaces now have static and instance methods with code. Developers should not have to remember which modifiers are required and which are implied. This check allows the simpler alternative approach to be adopted where the implied modifiers must always be coded explicitly.