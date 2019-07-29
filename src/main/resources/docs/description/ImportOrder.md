Checks the ordering/grouping of imports. Features are:

* groups type/static imports: ensures that groups of imports come in a specific order (e.g., java. comes first, javax. comes second, then everything else)
* adds a separation between type import groups : ensures that a blank line sit between each group
* type/static import groups aren't separated internally: ensures that each group aren't separated internally by blank line or comment
* sorts type/static imports inside each group: ensures that imports within each group are in lexicographic order
* sorts according to case: ensures that the comparison between imports is case sensitive, in [ASCII sort order](https://en.wikipedia.org/wiki/ASCII#Order)
* arrange static imports: ensures the relative order between type imports and static imports (see [import orders](property_types.html#importOrder))
