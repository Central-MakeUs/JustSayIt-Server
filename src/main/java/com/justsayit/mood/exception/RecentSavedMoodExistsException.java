package com.justsayit.mood.exception;

public class RecentSavedMoodExistsException extends RuntimeException {

    public RecentSavedMoodExistsException() {
    }

    public RecentSavedMoodExistsException(String message) {
        super(message);
    }

    public RecentSavedMoodExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecentSavedMoodExistsException(Throwable cause) {
        super(cause);
    }
}
