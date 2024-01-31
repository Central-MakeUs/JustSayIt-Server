package com.justsayit.story.exception;

public class EmpathizeMyStoryException extends RuntimeException {

    public EmpathizeMyStoryException() {
    }

    public EmpathizeMyStoryException(String message) {
        super(message);
    }

    public EmpathizeMyStoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmpathizeMyStoryException(Throwable cause) {
        super(cause);
    }
}
