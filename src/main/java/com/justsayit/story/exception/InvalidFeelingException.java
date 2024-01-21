package com.justsayit.story.exception;

public class InvalidFeelingException extends RuntimeException {

    public InvalidFeelingException() {
    }

    public InvalidFeelingException(String message) {
        super(message);
    }

    public InvalidFeelingException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidFeelingException(Throwable cause) {
        super(cause);
    }
}
