package com.example.pkg;

public class CodacyCheckstyle {

    @SuppressWarnings("all")
    public void importantMethod() {
        try {
            throw new RuntimeException("error");
        } catch (Exception ex) {
            // ignore
        }
    }

}
