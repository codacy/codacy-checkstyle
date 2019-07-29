Checks that non-whitespace characters are separated by no more than one
whitespace. Separating characters by tabs or multiple spaces will be
reported. Currently the check doesn't permit horizontal alignment. To inspect
whitespaces before and after comments, set the property
`validateComments` to true.

Setting `validateComments` to false will ignore cases like:


    int i;  // Multiple whitespaces before comment tokens will be ignored.
    private void foo(int  /* whitespaces before and after block-comments will be
    ignored */  i) {
            
Sometimes, users like to space similar items on different lines to the same
column position for easier reading. This feature isn't supported by this
check, so both braces in the following case will be reported as violations.


    public long toNanos(long d)  { return d;             } // 2 violations
    public long toMicros(long d) { return d / (C1 / C0); }
            
