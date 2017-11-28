Since Checkstyle 5.7

Checks properties files for duplicated properties.

Rationale: Multiple property keys usually appear after merge or rebase of several branches. While there are no errors in runtime, there can be a confusion due to having different values for the duplicated properties.