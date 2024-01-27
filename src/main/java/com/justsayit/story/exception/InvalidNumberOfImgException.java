package com.justsayit.story.exception;

public class InvalidNumberOfImgException extends RuntimeException {

    public InvalidNumberOfImgException() {
    }

    public InvalidNumberOfImgException(String message) {
        super(message);
    }

    public InvalidNumberOfImgException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidNumberOfImgException(Throwable cause) {
        super(cause);
    }
}
