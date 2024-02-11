package com.justsayit.member.exception;

public class InvalidAppleIdentityToken extends RuntimeException {

    public InvalidAppleIdentityToken() {
    }

    public InvalidAppleIdentityToken(String message) {
        super(message);
    }

    public InvalidAppleIdentityToken(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAppleIdentityToken(Throwable cause) {
        super(cause);
    }
}
