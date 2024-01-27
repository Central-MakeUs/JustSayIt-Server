package com.justsayit.story.exception;

public class InvalidFeelingCodeException extends RuntimeException {

    public InvalidFeelingCodeException() {
    }

    public InvalidFeelingCodeException(String message) {
        super(message);
    }

    public InvalidFeelingCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidFeelingCodeException(Throwable cause) {
        super(cause);
    }
}
