package com.gary.commercial.exception;

public class ToastException extends BaseException {
    private static final long serialVersionUID = -4134318918697162517L;

    public ToastException() {
        super();
    }

    public ToastException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ToastException(String detailMessage) {
        super(detailMessage);
    }

    public ToastException(Throwable throwable) {
        super(throwable);
    }
}
