Since Checkstyle 3.2

Checks that array initialization contains a trailing comma.

    int[] a = new int[]
    {
        1,
        2,
        3,
    };

The check demands a comma at the end if neither left nor right curly braces are on the same line as the last element of the array.

    return new int[] { 0 };
    return new int[] { 0
        };
    return new int[] {
        0 };

Rationale: Putting this comma in makes it easier to change the order of the elements or add new elements on the end. Main benefit of a trailing comma is that when you add new entry to an array, no surrounding lines are changed.

    {
        100000000000000000000,
        200000000000000000000, // OK
    }
    
    {
        100000000000000000000,
        200000000000000000000,
        300000000000000000000,  // Just this line added, no other changes
    }

If closing brace is on the same line as training comma, this benefit is gone (as the Check does not demand a certain location of curly braces the following two cases will not produce a violation):

    {100000000000000000000,
        200000000000000000000,} // Trailing comma not needed, line needs to be modified anyway
    
    {100000000000000000000,
        200000000000000000000, // Modified line
        300000000000000000000,} // Added line

If opening brace is on the same line as training comma there's also (more arguable) problem:

    {100000000000000000000, // Line cannot be just duplicated to slightly modify entry
    }
    
    {100000000000000000000,
        100000000000000000001, // More work needed to duplicate
    }