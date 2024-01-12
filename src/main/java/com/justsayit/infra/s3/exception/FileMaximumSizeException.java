package com.justsayit.infra.s3.exception;

public class FileMaximumSizeException extends RuntimeException {

    public FileMaximumSizeException() {
    }

    public FileMaximumSizeException(String message) {
        super(message);
    }

    public FileMaximumSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileMaximumSizeException(Throwable cause) {
        super(cause);
    }
}
