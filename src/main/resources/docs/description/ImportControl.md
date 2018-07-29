Since Checkstyle 4.0

Controls what can be imported in each package and file. Useful for ensuring that application layering rules are not violated, especially on large projects.

You can control imports based on the a package name or based on the file name. When controlling packages, all files and sub-packages in the declared package will be controlled by this check. To specify differences between a main package and a sub-package, you must define the sub-package inside the main package. When controlling file, only the file name is considered and only files processed by TreeWalker. The file's extension is ignored.

Short description of the behaviour:

 *  Check starts checking from the longest matching subpackage (later 'current subpackage') or the first file name match described inside import control file to package defined in class file.
    
     *  The longest matching subpackage is found by starting with the root package and examining if the any of the sub-packages or file definitions match the current class' package or file name.
     *  If a file name is matched first, that is considered the longest match and becomes the current file/subpackage.
     *  If another subpackage is matched, then it's subpackages and file names are examined for the next longest match and the process repeats recursively.
     *  If no subpackages or file names are matched, the current subpackage is then used.
 *  Order of rules in the same subpackage/root are defined by the order of declaration in the XML file, which is from top (first) to bottom (last).
 *  If there is matching allow/disallow rule inside the current file/subpackage then the Check returns the first "allowed" or "disallowed" message.
 *  If there is no matching allow/disallow rule inside the current file/subpackage then it continues checking in the parent subpackage.
 *  If there is no matching allow/disallow rule in any of the files/subpackages, including the root level (import-control), then the import is disallowed by default.

The DTD for a import control XML document is at [ https://checkstyle.org/dtds/import\_control\_1\_4.dtd][https_checkstyle.org_dtds_import_control_1_4.dtd]. It contains documentation on each of the elements and attributes.

The check validates a XML document when it loads the document. To validate against the above DTD, include the following document type declaration in your XML document:

    <!DOCTYPE import-control PUBLIC
        "-//Checkstyle//DTD ImportControl Configuration 1.4//EN"
        "https://checkstyle.org/dtds/import_control_1_4.dtd">


[https_checkstyle.org_dtds_import_control_1_4.dtd]: https://checkstyle.org/dtds/import_control_1_4.dtd