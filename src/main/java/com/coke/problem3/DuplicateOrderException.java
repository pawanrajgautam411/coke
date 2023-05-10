package com.coke.problem3;

public class DuplicateOrderException extends RuntimeException {
    public DuplicateOrderException() {
    }

    public DuplicateOrderException(String message) {
        super(message);
    }

    public DuplicateOrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateOrderException(Throwable cause) {
        super(cause);
    }

    public DuplicateOrderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
