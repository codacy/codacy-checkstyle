Disallows assignment of parameters.

Rationale: Parameter assignment is often considered poor programming
practice. Forcing developers to declare parameters as final is often
onerous. Having a check ensure that parameters are never assigned
would give the best of both worlds.
