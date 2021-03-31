package com.eugeneze.models;

public class PopulationDidntSupportException extends Exception {

    public PopulationDidntSupportException() {
    }

    public PopulationDidntSupportException(String message) {
        super(message);
    }

    public PopulationDidntSupportException(String message, Throwable cause) {
        super(message, cause);
    }

    public PopulationDidntSupportException(Throwable cause) {
        super(cause);
    }

    public PopulationDidntSupportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
