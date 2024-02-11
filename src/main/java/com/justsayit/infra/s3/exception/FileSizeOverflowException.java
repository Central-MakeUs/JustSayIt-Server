package com.justsayit.infra.s3.exception;

public class FileSizeOverflowException extends RuntimeException {

    public FileSizeOverflowException() {
    }

    public FileSizeOverflowException(String message) {
        super(message);
    }

    public FileSizeOverflowException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileSizeOverflowException(Throwable cause) {
        super(cause);
    }
}
