package com.issart.exception;

/**
 * Created by vurazov on 29.04.2016.
 */
public class DuplicateUserException extends ServiceException {

    private final Error error;

    public DuplicateUserException() {
        this.error = Error.DUPLICATE_USER;
    }

    public DuplicateUserException(String message) {
        this.error = new Error(message);
    }

    @Override
    public Error getError() {
        return error;
    }
}
