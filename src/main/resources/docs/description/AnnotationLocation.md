Since Checkstyle 6.0

Check location of annotation on language elements. By default, Check enforce to locate annotations immediately after documentation block and before target element, annotation should be located on separate line from target element.

Attention: Annotations among modifiers are ignored (looks like false-negative) as there might be a problem with annotations for return types

    public @Nullable Long getStartTimeOrNull() { ... }

Such annotations are better to keep close to type. Due to limitations Checkstyle can not examine target of annotation.

Example:

    @Override
    @Nullable
    public String getNameIfPresent() { ... }