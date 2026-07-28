<div>

Checks that the groups of import declarations appear in the order
specified by the user. If there is an import but its group is not
specified in the configuration such an import should be placed at the
end of the import list.

</div>

The rule consists of:

1.  STATIC group. This group sets the ordering of static imports.
2.  SAME_PACKAGE(n) group. This group sets the ordering of the same
    package imports. Imports are considered on SAME_PACKAGE group if
    **n** first domains in package name and import name are identical:
    <div class="wrapper">

    ``` prettyprint
    package java.util.concurrent.locks;

    import java.io.File;
    import java.util.*; //#1
    import java.util.List; //#2
    import java.util.StringTokenizer; //#3
    import java.util.concurrent.*; //#4
    import java.util.concurrent.AbstractExecutorService; //#5
    import java.util.concurrent.locks.LockSupport; //#6
    import java.util.regex.Pattern; //#7
    import java.util.regex.Matcher; //#8
            
    ```

    </div>

    If we have SAME_PACKAGE(3) on configuration file, imports \#4-6 will
    be considered as a SAME_PACKAGE group (java.util.concurrent.\*,
    java.util.concurrent.AbstractExecutorService,
    java.util.concurrent.locks.LockSupport). SAME_PACKAGE(2) will
    include \#1-8. SAME_PACKAGE(4) will include only \#6.
    SAME_PACKAGE(5) will result in no imports assigned to SAME_PACKAGE
    group because actual package java.util.concurrent.locks has only 4
    domains.
3.  THIRD_PARTY_PACKAGE group. This group sets ordering of third party
    imports. Third party imports are all imports except STATIC,
    SAME_PACKAGE(n), STANDARD_JAVA_PACKAGE and SPECIAL_IMPORTS.
4.  STANDARD_JAVA_PACKAGE group. By default, this group sets ordering of
    standard java/javax imports.
5.  SPECIAL_IMPORTS group. This group may contain some imports that have
    particular meaning for the user.
