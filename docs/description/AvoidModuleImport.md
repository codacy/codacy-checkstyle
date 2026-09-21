<div>

Checks that there are no module imports.

</div>

Rationale: Module import declarations (`import module M;`) import, on
demand, every public top level type exported by a module and by any
modules it transitively reads. This is a much broader, less explicit
surface than single type or on demand package imports, making it harder
to tell where a type comes from, and it increases the risk of ambiguous
references between same named types in different exported packages.
Disallowing module imports keeps imports explicit and predictable.
