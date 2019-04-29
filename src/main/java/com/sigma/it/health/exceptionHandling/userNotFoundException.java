package com.sigma.it.health.exceptionHandling;

public class userNotFoundException extends  RuntimeException {

    public userNotFoundException(String message) {
        super(message);
    }

    public userNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public userNotFoundException(Throwable cause) {
        super(cause);
    }
}
