package com.demo.lunit.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseError {
    private Integer errorCode;
    private String errorDescription;
}
