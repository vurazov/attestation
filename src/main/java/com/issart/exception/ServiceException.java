package com.issart.exception;

/**
 * Generic persistence exception
 */
public abstract class ServiceException extends Exception {

    public abstract Error getError();

    @Override
    public String getMessage() {
        return getError().getMessage();
    }

    @Override
    public String toString() {
        return getError().getMessage();
    }
}
