package com.haileysun.bookmyhotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    /**
     * This method takes in the Exception object that was thrown and the WebRequest object
     * associated with the request that caused the exception.
     * It then creates a new ResponseEntity with the error message and HttpStatus.CONFLICT status code.
     */
    // TODO - "request" 没被用
    public final ResponseEntity<String> handleUserAlreadyExistExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        /**
         * The "ResponseEntity<String>" return type indicates that this method will return
         * an HTTP response entity with a body that contains a String.
         * The HTTP response entity can include status codes, headers, and a response body.
         */
    }

    @ExceptionHandler(UserNotExistException.class)
    public final ResponseEntity<String> handleUserNotExistException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AWSS3UploadException.class)
    public final ResponseEntity<String> handleAWSS3UploadException(Exception e, WebRequest request) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
