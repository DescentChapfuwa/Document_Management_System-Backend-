package com.filehandlingsystem.fileHandling.exception;

public class TokenNotFound extends RuntimeException{

    public TokenNotFound(String message) {
        super(message);
    }

    public TokenNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
