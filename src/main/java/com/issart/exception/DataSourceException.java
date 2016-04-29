package com.issart.exception;

/**
 * Base data source exception class. To avoid dependency concrete ORM
 */
public class DataSourceException extends Exception {

    public DataSourceException(String message) {
        super(message);
    }

    public DataSourceException(String message, Throwable throwable) {
        super(message, throwable);
    }

    protected DataSourceException(Throwable throwable) {
        super(throwable);
    }
}
