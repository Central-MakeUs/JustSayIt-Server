package com.justsayit.story.exception;

public class NotMyStoryException extends RuntimeException {

    public NotMyStoryException() {
    }

    public NotMyStoryException(String message) {
        super(message);
    }

    public NotMyStoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotMyStoryException(Throwable cause) {
        super(cause);
    }
}
