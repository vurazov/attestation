package com.issart.exception;

/**
 * Exception is thrown when the handler cannot be deleted
 */
public class ConstraintViolationException extends ServiceException {

    @Override
    public Error getError() {
        return Error.CONSTRAINT_VIOLATION;
    }
}
