package com.justsayit.mood.exception;

public class OneMinuteWithinException extends RuntimeException {

    public OneMinuteWithinException() {
    }

    public OneMinuteWithinException(String message) {
        super(message);
    }

    public OneMinuteWithinException(String message, Throwable cause) {
        super(message, cause);
    }

    public OneMinuteWithinException(Throwable cause) {
        super(cause);
    }
}
