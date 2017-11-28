Since Checkstyle 3.1

Checks that there are no [ "magic numbers"][_magic numbers] where a magic number is a numeric literal that is not defined as a constant. By default, -1, 0, 1, and 2 are not considered to be magic numbers.

It is fine to have one constant defining multiple numeric literals within one expression:

    static final int SECONDS_PER_DAY = 24 * 60 * 60;
    static final double SPECIAL_RATIO = 4.0 / 3.0;
    static final double SPECIAL_SUM = 1 + Math.E;
    static final double SPECIAL_DIFFERENCE = 4 - Math.PI;
    static final Border STANDARD_BORDER = BorderFactory.createEmptyBorder(3, 3, 3, 3);
    static final Integer ANSWER_TO_THE_ULTIMATE_QUESTION_OF_LIFE = new Integer(42);


[_magic numbers]: https://en.wikipedia.org/wiki/Magic_number_%28programming%29