Checks visibility of class members. Only static final, immutable or
annotated by specified annotation members may be public; other class
members must be private unless the property `protectedAllowed` or
`packageAllowed` is set.

Public members are not flagged if the name matches the public member
regular expression (contains `"^serialVersionUID$"` by default).

Note that Checkstyle 2 used to include `"^f[A-Z][a-zA-Z0-9]*$"` in the
default pattern to allow names used in container-managed persistence for
Enterprise JavaBeans (EJB) 1.1 with the default settings. With EJB 2.0
it is no longer necessary to have public access for persistent fields,
so the default has been changed.

Rationale: Enforce encapsulation.

Check also has options making it less strict:

**ignoreAnnotationCanonicalNames** - the list of annotations which
ignore variables in consideration. If user will provide short annotation
name that type will match to any named the same type without
consideration of package.

**allowPublicFinalFields** - which allows public final fields.

**allowPublicImmutableFields** - which allows immutable fields to be
declared as public if defined in final class.

Field is known to be immutable if:

  - It's declared as final
  - Has either a primitive type or instance of class user defined to be
    immutable (such as String, ImmutableCollection from Guava and etc)

Classes known to be immutable are listed in
**immutableClassCanonicalNames** by their canonical names.

Property Rationale: Forcing all fields of class to have private modifier
by default is good in most cases, but in some cases it drawbacks in too
much boilerplate get/set code. One of such cases are immutable classes.

Restriction: Check doesn't check if class is immutable, there's no
checking if accessory methods are missing and all fields are immutable,
we only check if current field is immutable or final. Under the flag
**allowPublicImmutableFields**, the enclosing class must also be final,
to encourage immutability. Under the flag **allowPublicFinalFields**,
the final modifier on the enclosing class is optional.

Star imports are out of scope of this Check. So if one of type imported
via star import collides with user specified one by its short name -
there won't be Check's violation.
