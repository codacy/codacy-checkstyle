<div>

Checks the ordering and placement of module import declarations.
Features are:

</div>

- position of module imports: ensures that module imports are placed
  above or below all type and static imports (see
  [ModuleImportOrderOption](https://checkstyle.org/property_types.html#ModuleImportOrderOption))
- sorts module imports: ensures that module imports are sorted
  lexicographically by qualified module name, in [ASCII sort
  order](https://en.wikipedia.org/wiki/ASCII#Order)
- adds a separation between module imports and other imports: ensures
  that the module import block is separated from type and static imports
  by, at least, one blank line or comment

This check only validates module imports. It observes type and static
imports to locate the boundary of the module import block, but does not
validate their order. Use `ImportOrder` alongside this check for those.
