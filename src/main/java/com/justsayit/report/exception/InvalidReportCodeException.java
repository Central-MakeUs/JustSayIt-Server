package com.justsayit.report.exception;

public class InvalidReportCodeException extends RuntimeException {

    public InvalidReportCodeException() {
    }

    public InvalidReportCodeException(String message) {
        super(message);
    }

    public InvalidReportCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidReportCodeException(Throwable cause) {
        super(cause);
    }
}
