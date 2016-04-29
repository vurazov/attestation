package com.issart.exception;

/**
 * Exception is thrown when passed handler contains invalid parameters
 */
public class InvalidParameterException extends ServiceException {

    private final Error error;

    public InvalidParameterException() {
        this.error = Error.INVALID_PARAMETER;
    }

    public InvalidParameterException(String message) {
        this.error = new Error(message);
    }

    @Override
    public Error getError() {
        return error;
    }
}
