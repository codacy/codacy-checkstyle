Verifies that both the @Deprecated annotation is present and the @deprecated javadoc tag are present when either one is present.

Both ways of flagging deprecation serve their own purpose. The @Deprecated annotation is used for compilers and development tools. The @deprecated javadoc tag is used to document why something is deprecated and what, if any, alternatives exist.

In order to properly mark something as deprecated both forms of deprecation should be present.

Package deprecation is a exception to the rule of always using the javadoc tag and annotation to deprecate. Only the package-info.java file can contain a Deprecated annotation and it CANNOT contain a deprecated javadoc tag. This is the case with Sun's javadoc tool released with JDK 1.6.0\_11. As a result, this check does not deal with Deprecated packages in any way. **No official documentation was found confirming this behavior is correct (of the javadoc tool).**