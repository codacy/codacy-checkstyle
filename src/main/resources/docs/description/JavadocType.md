Since Checkstyle 3.0

Checks Javadoc comments for class and interface definitions. By default, does not check for author or version tags. The scope to verify is specified using the `Scope` class and defaults to `Scope.PRIVATE`. To verify another scope, set property scope to one of the `Scope` constants. To define the format for an author tag or a version tag, set property authorFormat or versionFormat respectively to a [ regular expression][regular expression].

Does not perform checks for author and version tags for inner classes, as they should be redundant because of outer class.

Error messages about type parameters for which no param tags are present can be suppressed by defining property `allowMissingParamTags`.


[regular expression]: https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html