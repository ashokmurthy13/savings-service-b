package com.kadmos.savings.app.exception;

public class SavingsBaseException extends RuntimeException {

    public SavingsBaseException() {
    }

    public SavingsBaseException(String message) {
        super(message);
    }

    public SavingsBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public SavingsBaseException(Throwable cause) {
        super(cause);
    }

    public SavingsBaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
