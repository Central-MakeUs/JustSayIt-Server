package com.justsayit.story.exception;

public class EmptyMainContentException extends RuntimeException {

    public EmptyMainContentException() {
    }

    public EmptyMainContentException(String message) {
        super(message);
    }

    public EmptyMainContentException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyMainContentException(Throwable cause) {
        super(cause);
    }
}
