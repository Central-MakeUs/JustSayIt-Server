package com.justsayit.story.exception;

public class NoEmpathizeException extends RuntimeException {

    public NoEmpathizeException() {
    }

    public NoEmpathizeException(String message) {
        super(message);
    }

    public NoEmpathizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoEmpathizeException(Throwable cause) {
        super(cause);
    }
}
