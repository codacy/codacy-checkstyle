Since Checkstyle 5.8

Restrict using [ Unicode escapes][Unicode escapes] (e.g. \\u221e). It is possible to allow using escapes for [ non-printable(control) characters][non-printable_control_ characters]. Also, this check can be configured to allow using escapes if trail comment is present. By the option it is possible to allow using escapes if literal contains only them.


[Unicode escapes]: https://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.3
[non-printable_control_ characters]: https://en.wiktionary.org/wiki/Appendix:Control_characters