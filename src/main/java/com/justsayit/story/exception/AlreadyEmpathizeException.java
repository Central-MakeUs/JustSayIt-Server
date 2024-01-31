package com.justsayit.story.exception;

public class AlreadyEmpathizeException extends RuntimeException {

    public AlreadyEmpathizeException() {
    }

    public AlreadyEmpathizeException(String message) {
        super(message);
    }

    public AlreadyEmpathizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyEmpathizeException(Throwable cause) {
        super(cause);
    }
}
