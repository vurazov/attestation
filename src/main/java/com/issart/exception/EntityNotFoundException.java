package com.issart.exception;

/**
 * Exception is thrown when handler deleted or doesn't exist
 */
public class EntityNotFoundException extends ServiceException {

    private final Error error;

    public EntityNotFoundException() {
        this.error = Error.NOT_FOUND;
    }

    public EntityNotFoundException(String message) {
        this.error = new Error(message);
    }

    @Override
    public Error getError() {
        return error;
    }
}
