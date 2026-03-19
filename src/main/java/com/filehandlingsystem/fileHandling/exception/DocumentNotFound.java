package com.filehandlingsystem.fileHandling.exception;

public class DocumentNotFound extends RuntimeException{
    public DocumentNotFound(String message) {
        super(message);
    }

    public DocumentNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
