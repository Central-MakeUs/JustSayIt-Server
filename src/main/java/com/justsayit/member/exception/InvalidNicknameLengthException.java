package com.justsayit.member.exception;

public class InvalidNicknameLengthException extends RuntimeException {

    public InvalidNicknameLengthException() {
    }

    public InvalidNicknameLengthException(String message) {
        super(message);
    }

    public InvalidNicknameLengthException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidNicknameLengthException(Throwable cause) {
        super(cause);
    }
}
