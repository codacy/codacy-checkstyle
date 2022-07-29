Checks the style of array type definitions. Some like Java style:
`public static void main(String[] args)` and some like C style:
`public static void main(String args[])`.

By default, the Check enforces Java style.

This check strictly enforces only Java style for method return types
regardless of the value for 'javaStyle'. For example,
`byte[] getData()`. This is because C doesn't compile methods with array
declarations on the name.
