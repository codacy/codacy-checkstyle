<div>

Verifies that the annotation `@Deprecated` and the Javadoc tag
`@deprecated` are both present when either of them is present.

</div>

Both ways of flagging deprecation serve their own purpose. The
@Deprecated annotation is used for compilers and development tools. The
@deprecated javadoc tag is used to document why something is deprecated
and what, if any, alternatives exist.

In order to properly mark something as deprecated both forms of
deprecation should be present.

Package deprecation is an exception to the rule of always using the
javadoc tag and annotation to deprecate. It is not clear if the javadoc
tool will support it or not as newer versions keep flip-flopping on if
it is supported or will cause an error. See
[JDK-8160601](https://bugs.openjdk.org/browse/JDK-8160601). The
deprecated javadoc tag is currently the only way to say why the package
is deprecated and what to use instead. Until this is resolved, if you
don't want to print violations on package-info, you can use a
[filter](../../filters/index.html) to ignore these files until the
javadoc tool faithfully supports it. An example config using
SuppressionSingleFilter is:

    <!-- required till https://bugs.openjdk.org/browse/JDK-8160601 -->
    <module name="SuppressionSingleFilter">
        <property name="checks" value="MissingDeprecatedCheck"/>
        <property name="files" value="package-info\.java"/>
    </module>
