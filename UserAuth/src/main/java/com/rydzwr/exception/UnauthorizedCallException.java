package com.rydzwr.exception;

public class UnauthorizedCallException extends RuntimeException {

    public UnauthorizedCallException() {
        this("Unauthorized!");
    }
    public UnauthorizedCallException(String message) {
        super(message);
    }

    public UnauthorizedCallException(Throwable cause) {
        super(cause);
    }
}
