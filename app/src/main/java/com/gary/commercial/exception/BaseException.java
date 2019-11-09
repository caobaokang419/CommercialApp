package com.gary.commercial.exception;

public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -2299984118501502345L;

    public BaseException() {
        super();
    }

    public BaseException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public BaseException(String detailMessage) {
        super(detailMessage);
    }

    public BaseException(Throwable throwable) {
        super(throwable);
    }
}
