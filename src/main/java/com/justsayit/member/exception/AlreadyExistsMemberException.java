package com.justsayit.member.exception;

public class AlreadyExistsMemberException extends RuntimeException {

    public AlreadyExistsMemberException() {
    }

    public AlreadyExistsMemberException(String message) {
        super(message);
    }

    public AlreadyExistsMemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistsMemberException(Throwable cause) {
        super(cause);
    }
}
