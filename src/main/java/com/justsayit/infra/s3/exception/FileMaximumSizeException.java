package com.justsayit.infra.s3.exception;

public class FailToUploadImage extends RuntimeException {

    public FailToUploadImage() {
    }

    public FailToUploadImage(String message) {
        super(message);
    }

    public FailToUploadImage(String message, Throwable cause) {
        super(message, cause);
    }

    public FailToUploadImage(Throwable cause) {
        super(cause);
    }
}
