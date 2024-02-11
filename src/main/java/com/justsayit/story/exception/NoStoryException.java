package com.justsayit.story.exception;

public class NoStoryException extends RuntimeException {

    public NoStoryException() {
    }

    public NoStoryException(String message) {
        super(message);
    }

    public NoStoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoStoryException(Throwable cause) {
        super(cause);
    }
}
