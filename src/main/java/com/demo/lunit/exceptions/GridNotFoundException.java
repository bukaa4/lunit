package com.demo.lunit.exceptions;

public class GridNotFoundException extends RuntimeException {

    public GridNotFoundException(String message){
        super(message);
    }
}