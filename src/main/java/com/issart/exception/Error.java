package com.issart.exception;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Error handler
 */
@JsonAutoDetect
public class Error {
    public static final Error OK = new Error("OK");
    public static final Error NOT_FOUND = new Error("Entity not found");
    public static final Error CONSTRAINT_VIOLATION = new Error("Entity constraint violation");
    public static final Error INVALID_PARAMETER = new Error("Invalid parameter");
    public static final Error INVALID_CREDENTIALS = new Error("Invalid user credentials");
    public static final Error INVALID_USER = new Error("Invalid user's data");
    public static final Error NO_ACCESS = new Error("No access to Entity");
    public static final Error DUPLICATE_USER = new Error("User already exists");
    public static final Error NO_USER = new Error("User is not found");

    private String message;

    @JsonCreator
    public Error(@JsonProperty("Message") String message) {
        this.message = message;
    }

    public Error() {

    }

    @JsonProperty("Message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" + message + '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if ((object == null) || (getClass() != object.getClass()))
            return false;
        Error error = (Error) object;
        return !(message != null ? !message.equals(error.message) : (error.message != null));
    }

}
/*
public enum Error {

    USER_WITH_LOGIN_NOT_FOUND("RsUser with login '%s' doesn't found in the base.", 404),
    USER_ALREADY_EXISTS("RsUser with login '%s' already exists in system.", 400),
    USER_IS_NOT_AUTHORIZED("RsUser is not authorized", 404),
    INVALID_PARAMETER_CONFIGURATION("Parameter '%s' has illegal value. Value should be %s", 400),
    INVALID_PAREMETER("Parameter '%s' has illegal value or null", 400),
    WRONG_PASSWORD("Wrong password for user", 400),
    UNKNOWN_ERROR("Unknown error: %s.", 500);

    public final int code;
    private final String message;

    Error(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String format(Object... args) {
        return String.format(message, args);
    }
};
*/