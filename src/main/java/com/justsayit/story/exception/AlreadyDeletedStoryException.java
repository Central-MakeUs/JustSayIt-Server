package com.justsayit.story.exception;

public class AlreadyDeletedStoryException extends RuntimeException {

    public AlreadyDeletedStoryException() {
    }

    public AlreadyDeletedStoryException(String message) {
        super(message);
    }

    public AlreadyDeletedStoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyDeletedStoryException(Throwable cause) {
        super(cause);
    }
}
