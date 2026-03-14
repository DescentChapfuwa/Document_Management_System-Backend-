package com.filehandlingsystem.fileHandling.dto;

import java.time.LocalDateTime;

public class ErrorResponseDto {
    private LocalDateTime timeStamp;

    private int staus;

    private String error;

    private String message;

    private String path;

    public ErrorResponseDto(int staus,String error,String message,String path){
        this.timeStamp = LocalDateTime.now();
        this.staus = staus;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public int getStaus() {
        return staus;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public String getError() {
        return error;
    }
}
