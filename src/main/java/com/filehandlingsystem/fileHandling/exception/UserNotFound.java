package com.filehandlingsystem.fileHandling.exception;

public class UserNotFound extends RuntimeException{

    public UserNotFound(String message) {
        super(message);
    }

    public UserNotFound(String message, Throwable cause) {
        super(message, cause);
    }

}
