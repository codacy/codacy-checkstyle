Checks the padding of an empty for initializer; that is whether a white
space is required at an empty for initializer, or such white space is
forbidden. No check occurs if there is a line wrap at the initializer,
as in

``` 
for (
      ; i < j; i++, j--)
        
```
