package com.rydzwr.exception;

public class UnauthorizedCallException extends RuntimeException {
    public UnauthorizedCallException(String message) {
        super(message);
    }

    public UnauthorizedCallException(Throwable cause) {
        super(cause);
    }
}
