package com.justsayit.story.exception;

public class InvalidBodyTextException extends RuntimeException {

    public InvalidBodyTextException() {
    }

    public InvalidBodyTextException(String message) {
        super(message);
    }

    public InvalidBodyTextException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidBodyTextException(Throwable cause) {
        super(cause);
    }
}
