package com.justsayit.member.exception;

public class NoMemberException extends RuntimeException {

    public NoMemberException() {
    }

    public NoMemberException(String message) {
        super(message);
    }

    public NoMemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMemberException(Throwable cause) {
        super(cause);
    }
}
