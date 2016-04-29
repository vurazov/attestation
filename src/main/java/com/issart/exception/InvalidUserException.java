package com.issart.exception;

public class InvalidUserException extends ServiceException {

    private final Error error;

    public InvalidUserException() {
        this.error = Error.INVALID_USER;
    }

    public InvalidUserException(String message) {
        this.error = new Error(message);
    }

    @Override
    public Error getError() {
        return error;
    }
}
