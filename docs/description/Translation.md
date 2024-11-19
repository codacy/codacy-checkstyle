<div>

Ensures the correct translation of code by checking property files for
consistency regarding their keys. Two property files describing one and
the same context are consistent if they contain the same keys.
TranslationCheck also can check an existence of required translations
which must exist in project, if `requiredTranslations` option is used.

</div>

Consider the following properties file in the same directory:

    #messages.properties
    hello=Hello
    cancel=Cancel

    #messages_de.properties
    hell=Hallo
    ok=OK
            

The Translation check will find the typo in the German `hello` key, the
missing `ok` key in the default resource file and the missing `cancel`
key in the German resource file:

    messages_de.properties: Key 'hello' missing.
    messages_de.properties: Key 'cancel' missing.
    messages.properties: Key 'hell' missing.
    messages.properties: Key 'ok' missing.
