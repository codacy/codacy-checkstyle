Checks for empty blocks. This check does not validate sequential blocks.

Sequential blocks won't be checked. Also, no violations for fallthrough:


    switch (a) {
      case 1:                          // no violation
      case 2:                          // no violation
      case 3: someMethod(); { }        // no violation
      default: break;
    }
            
This check processes LITERAL_CASE and LITERAL_DEFAULT separately.
So, if tokens=LITERAL_DEFAULT, following code will not trigger any violation,
as the empty block belongs to LITERAL_CASE:

Configuration:


    <module name="EmptyBlock">
      <property name="tokens" value="LITERAL_DEFAULT"/>
    </module>
            
Result:


    switch (a) {
      default:        // no violation for "default:" as empty block belong to "case 1:"
      case 1: { }
    }
            
