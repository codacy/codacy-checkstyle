Detects duplicated keys in properties files.

Rationale: Multiple property keys usually appear after merge or rebase
of several branches. While there are no problems in runtime, there can
be a confusion due to having different values for the duplicated
properties.