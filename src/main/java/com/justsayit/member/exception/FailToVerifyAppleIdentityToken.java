package com.justsayit.member.exception;

public class FailToVerifyAppleIdentityToken extends RuntimeException {

    public FailToVerifyAppleIdentityToken() {
    }

    public FailToVerifyAppleIdentityToken(String message) {
        super(message);
    }

    public FailToVerifyAppleIdentityToken(String message, Throwable cause) {
        super(message, cause);
    }

    public FailToVerifyAppleIdentityToken(Throwable cause) {
        super(cause);
    }
}
