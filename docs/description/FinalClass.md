Checks that a class which has only private constructors is declared as
final. Doesn't check for classes nested in interfaces or annotations, as
they are always `final` there.
