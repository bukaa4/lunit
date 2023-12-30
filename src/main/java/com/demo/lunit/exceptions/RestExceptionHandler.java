package com.demo.lunit.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class RestExceptionHandler {

    private ResponseError responseError;

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ResponseError> handleUserNotFoundException() {
        responseError = new ResponseError(400, "User not found.");
        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SlideNotFoundException.class)
    public ResponseEntity<ResponseError> handleSlideNotFoundException() {
        responseError = new ResponseError(400, "Slide not found.");
        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = GridNotFoundException.class)
    public ResponseEntity<ResponseError> handleGridNotFoundException() {
        responseError = new ResponseError(400, "Grid not found.");
        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SlideUploadException.class)
    public ResponseEntity<ResponseError> handleSlideUploadException() {
        responseError = new ResponseError(400, "Uploading slides are failed.");
        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DbException.class)
    public ResponseEntity<ResponseError> handleDatabaseException() {
        responseError = new ResponseError(500, "Something wrong in DB.");
        return new ResponseEntity<>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = InvalidRequestException.class)
    public ResponseEntity<ResponseError> handleInvalidRequestException() {
        responseError = new ResponseError(400, "Request is not valid. Please check the request object.");
        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AIClientException.class)
    public ResponseEntity<ResponseError> handleAiClientException() {
        responseError = new ResponseError(500, "AI client is failed.");
        return new ResponseEntity<>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
