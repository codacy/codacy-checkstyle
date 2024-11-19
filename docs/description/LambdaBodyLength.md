<div>

Checks lambda body length.

</div>

Rationale: Similar to anonymous inner classes, if lambda body becomes
very long it is hard to understand and to see the flow of the method
where the lambda is defined. Therefore, long lambda body should usually
be extracted to method.
