package com.justsayit.member.exception;

public class MemberNicknameOverflowException extends RuntimeException {

    public MemberNicknameOverflowException() {
    }

    public MemberNicknameOverflowException(String message) {
        super(message);
    }

    public MemberNicknameOverflowException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberNicknameOverflowException(Throwable cause) {
        super(cause);
    }
}
