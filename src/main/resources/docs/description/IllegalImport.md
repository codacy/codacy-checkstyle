Since Checkstyle 3.0

Checks for imports from a set of illegal packages. By default, the check rejects all `sun.*` packages since programs that contain direct calls to the `sun.*` packages are ["not guaranteed to work on all Java-compatible platforms"][not guaranteed to work on all Java-compatible platforms]. To reject other packages, set property `illegalPkgs` to a list of the illegal packages.


[not guaranteed to work on all Java-compatible platforms]: https://www.oracle.com/technetwork/java/faq-sun-packages-142232.html