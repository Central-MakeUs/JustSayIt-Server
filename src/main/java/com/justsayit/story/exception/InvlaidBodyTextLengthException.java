package com.justsayit.story.exception;

public class InvlaidBodyTextLengthException extends RuntimeException {

    public InvlaidBodyTextLengthException() {
    }

    public InvlaidBodyTextLengthException(String message) {
        super(message);
    }

    public InvlaidBodyTextLengthException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvlaidBodyTextLengthException(Throwable cause) {
        super(cause);
    }
}
