package com.eugeneze.models;

public class CityNotInTheListException extends Exception {

    public CityNotInTheListException() {
    }

    public CityNotInTheListException(String message) {
        super(message);
    }

    public CityNotInTheListException(String message, Throwable cause) {
        super(message, cause);
    }

    public CityNotInTheListException(Throwable cause) {
        super(cause);
    }

    public CityNotInTheListException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
