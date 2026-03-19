package com.filehandlingsystem.fileHandling.exception;

import com.filehandlingsystem.fileHandling.dto.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StorageException.class)
    public ResponseEntity<ErrorResponseDto> handleStorageException(StorageException exc, HttpServletRequest request){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                HttpStatus.NOT_FOUND.value(),
                "STORAGE_DIRECTORY_NOT_FOUND",
                exc.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(errorResponseDto,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleStorageFileNotFoundException(StorageFileNotFoundException exc, HttpServletRequest request){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                HttpStatus.NOT_FOUND.value(),
                "FILE_NOT_FOUND",
                exc.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(errorResponseDto,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DocumentNotFound.class)
    public ResponseEntity<ErrorResponseDto> handleDocumentNotFoundException(DocumentNotFound exc, HttpServletRequest request){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                HttpStatus.NOT_FOUND.value(),
                "DOCUMENT_NOT_FOUND",
                exc.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(errorResponseDto,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFound(UserNotFound exc, HttpServletRequest request){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                HttpStatus.NOT_FOUND.value(),
                "USER_NOT_FOUND",
                exc.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(errorResponseDto,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(Exception exc, HttpServletRequest request){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL_SERVER_ERROR,Something went wrong ,please call support",
                exc.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(errorResponseDto,HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
