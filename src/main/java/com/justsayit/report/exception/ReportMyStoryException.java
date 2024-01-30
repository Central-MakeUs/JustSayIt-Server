package com.justsayit.report.exception;

public class ReportMyStoryException extends RuntimeException {

    public ReportMyStoryException() {
    }

    public ReportMyStoryException(String message) {
        super(message);
    }

    public ReportMyStoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReportMyStoryException(Throwable cause) {
        super(cause);
    }
}
