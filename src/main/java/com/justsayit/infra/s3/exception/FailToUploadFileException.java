package com.justsayit.infra.s3.exception;

public class FailToUploadFileException extends RuntimeException {

    public FailToUploadFileException() {
    }

    public FailToUploadFileException(String message) {
        super(message);
    }

    public FailToUploadFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailToUploadFileException(Throwable cause) {
        super(cause);
    }
}
