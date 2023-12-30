package com.demo.lunit.exceptions;

public class SlideNotFoundException extends RuntimeException {

    public SlideNotFoundException(String message){
        super(message);
    }
}