package com.justsayit.mood.exception;

public class InvalidMoodCodeException extends RuntimeException {

    public InvalidMoodCodeException() {
    }

    public InvalidMoodCodeException(String message) {
        super(message);
    }

    public InvalidMoodCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidMoodCodeException(Throwable cause) {
        super(cause);
    }
}
