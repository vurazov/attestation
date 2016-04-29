package com.issart.exception;

public class IllegalAccessException extends ServiceException {

    @Override
    public Error getError() {
        return Error.NO_ACCESS;
    }
}
