Checks for implicit modifiers on nested types in classes and records.

This check is effectively the opposite of
[RedundantModifier](https://checkstyle.org/config_modifier.html#RedundantModifier).
It checks the modifiers on nested types in classes and records, ensuring
that certain modifiers are explicitly specified even though they are
actually redundant.

Nested enums, interfaces, and records within a class are always `static`
and as such the compiler does not require the `static` modifier. This
check provides the ability to enforce that the `static` modifier is
explicitly coded and not implicitly added by the compiler.

    public final class Person {
      enum Age {  // violation
        CHILD, ADULT
      }
    }
            

Rationale for this check: Nested enums, interfaces, and records are
treated differently from nested classes as they are only allowed to be
`static`. Developers should not need to remember this rule, and this
check provides the means to enforce that the modifier is coded
explicitly.
