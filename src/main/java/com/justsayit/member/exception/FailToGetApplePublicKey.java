package com.justsayit.member.exception;

public class FailToGetApplePublicKey extends RuntimeException {

    public FailToGetApplePublicKey() {
    }

    public FailToGetApplePublicKey(String message) {
        super(message);
    }

    public FailToGetApplePublicKey(String message, Throwable cause) {
        super(message, cause);
    }

    public FailToGetApplePublicKey(Throwable cause) {
        super(cause);
    }
}
