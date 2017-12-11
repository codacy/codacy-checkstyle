Since Checkstyle 5.0

This check allows you to specify what warnings that SuppressWarnings is not allowed to suppress. You can also specify a list of TokenTypes that the configured warning(s) cannot be suppressed on.

Limitations: This check does not consider conditionals inside the SuppressWarnings annotation.
For example: @SuppressWarnings((false) ? (true) ? "unchecked" : "foo" : "unused") According to the above example, the "unused" warning is being suppressed not the "unchecked" or "foo" warnings. All of these warnings will be considered and matched against regardless of what the conditional evaluates to.
The check also does not support code like `@SuppressWarnings("un" + "used")`, `@SuppressWarnings((String) "unused")` or `@SuppressWarnings({('u' + (char)'n') + (""+("used" + (String)"")),})`.