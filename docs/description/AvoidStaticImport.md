<div>

Checks that there are no static import statements.

</div>

Rationale: Importing static members can lead to naming conflicts between
class' members. It may lead to poor code readability since it may no
longer be clear what class a member resides in (without looking at the
import statement).
