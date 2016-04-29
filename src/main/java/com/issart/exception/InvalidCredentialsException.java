package com.issart.exception;

public class InvalidCredentialsException extends ServiceException {

    private final Error error;

    private InvalidCredentialsException(final Error error) {
        this.error = error;
    }

    public InvalidCredentialsException() {
        this(Error.INVALID_CREDENTIALS);
    }

    public InvalidCredentialsException(final String message) {
        this(new Error(message));
    }

    @Override
    public Error getError() {
        return error;
    }

}
