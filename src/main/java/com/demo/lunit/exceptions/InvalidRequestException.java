package com.demo.lunit.exceptions;

public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException(String message){
        super(message);
    }
}