package com.justsayit.story.exception;

public class InvalidEmotionCodeException extends RuntimeException {

    public InvalidEmotionCodeException() {
    }

    public InvalidEmotionCodeException(String message) {
        super(message);
    }

    public InvalidEmotionCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEmotionCodeException(Throwable cause) {
        super(cause);
    }
}
