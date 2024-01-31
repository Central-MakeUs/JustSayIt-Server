package com.justsayit.story.exception;

public class AlreadyCanceledEmpathyException extends RuntimeException {

    public AlreadyCanceledEmpathyException() {
    }

    public AlreadyCanceledEmpathyException(String message) {
        super(message);
    }

    public AlreadyCanceledEmpathyException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyCanceledEmpathyException(Throwable cause) {
        super(cause);
    }
}
