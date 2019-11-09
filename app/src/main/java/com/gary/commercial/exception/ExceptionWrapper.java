package com.gary.commercial.exception;

import android.support.annotation.NonNull;

public class ExceptionWrapper extends BaseException {

    public ExceptionWrapper(String remark, @NonNull Throwable throwable) {
        super(remark + ":\n" + throwable.getMessage(), throwable);
    }

    @Override
    public synchronized Throwable getCause() {
        return super.getCause().getCause();
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        return super.getCause().getStackTrace();
    }

    @Override
    public String toString() {
        return super.getCause().toString();
    }
}