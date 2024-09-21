package org.example;

public class BaseException extends Exception {

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public BaseException(String message) {
        this.message = message;
    }
}
