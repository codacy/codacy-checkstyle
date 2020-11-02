//#Patterns: EmptyLineSeparator: {"allowMultipleEmptyLines": false}
//#Issue: {"severity": "Info", "line": 7, "patternId": "EmptyLineSeparator"}
//#Issue: {"severity": "Info", "line": 13, "patternId": "EmptyLineSeparator"}
//#Issue: {"severity": "Info", "line": 16, "patternId": "EmptyLineSeparator"}

package org.example.EmptyLineSeparator;
import java.io.Serializable;

public class Something {
    private int fieldStandalone;

    private int fieldCoupled1;
    private String fieldCoupled2;


    public float fieldAfterMultipleEmptyLines;

    private void BarFoo() {

        System.out.print("This private method is NOT called!");
    } // unused

    private static final void ___FooBar() {


        System.out.print("This class member multiple empty lines.");
    }

           public void doSomething() {
        bar();
    }
}
