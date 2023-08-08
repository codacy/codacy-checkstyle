Ensures that identifies classes that can be effectively declared as
final are explicitly marked as final. The following are different types
of classes that can be identified:

1.  Private classes with no declared constructors.
2.  Classes with any modifier, and contains only private constructors.

Classes are skipped if:

1.  Class is Super class of some Anonymous inner class.
2.  Class is extended by another class in the same file.
