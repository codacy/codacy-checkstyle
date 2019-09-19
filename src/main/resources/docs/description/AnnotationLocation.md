Check location of annotation on language elements. By default, Check
enforce to locate annotations immediately after documentation block and
before target element, annotation should be located on separate line
from target element. This check also verifies that the annotations are
on the same indenting level as the annotated element if they are not on
the same line.

Attention: Elements that cannot have JavaDoc comments like local
variables are not in the scope of this check even though a token type
like `VARIABLE_DEF` would match them.

Attention: Annotations among modifiers are ignored (looks like
false-negative) as there might be a problem with annotations for return
types:

    public @Nullable Long getStartTimeOrNull() { ... }

Such annotations are better to keep close to type. Due to limitations,
Checkstyle can not examine the target of an annotation.

Example:

    @Override
    @Nullable
    public String getNameIfPresent() { ... }