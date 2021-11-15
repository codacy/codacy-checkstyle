Checks for empty blocks. This check does not validate sequential blocks.

Sequential blocks won't be checked. Also, no violations for fallthrough:

``` 
switch (a) {
  case 1:                          // no violation
  case 2:                          // no violation
  case 3: someMethod(); { }        // no violation
  default: break;
}
        
```

NOTE: This check processes LITERAL\_CASE and LITERAL\_DEFAULT
separately. Verification empty block is done for single most nearest
{@code case} or {@code default}.
