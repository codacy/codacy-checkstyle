Since Checkstyle 4.0

Controls what can be imported in each package. Useful for ensuring that application layering rules are not violated, especially on large projects.

Short description of the behaviour:

 *  Check starts checking from the longest matching subpackage (later 'current subpackage') described inside import control file to package defined in class file.
 *  If there is matching allow/disallow rule inside the current subpackage then the Check returns "allowed" or "disallowed" message.
 *  If there is no matching allow/disallow rule inside the current subpackage then it continues checking in the parent subpackage.
 *  If there is no matching allow/disallow rule in any of the subpackages, including the root level (import-control), then the import is disallowed by default.

The DTD for a import control XML document is at [ http://checkstyle.sourceforge.net/dtds/import\_control\_1\_3.dtd][http_checkstyle.sourceforge.net_dtds_import_control_1_3.dtd]. It contains documentation on each of the elements and attributes.

The check validates a XML document when it loads the document. To validate against the above DTD, include the following document type declaration in your XML document:

    <!DOCTYPE import-control PUBLIC
        "-//Puppy Crawl//DTD Import Control 1.3//EN"
        "http://checkstyle.sourceforge.net/dtds/import_control_1_3.dtd">


[http_checkstyle.sourceforge.net_dtds_import_control_1_3.dtd]: http://checkstyle.sourceforge.net/dtds/import_control_1_3.dtd