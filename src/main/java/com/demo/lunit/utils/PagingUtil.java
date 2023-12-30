package com.demo.lunit.utils;

import org.springframework.data.domain.Page;

import java.util.Map;

public class PagingUtil {
    public static Map<String, Object> buildPagingInfo(Map<String, Object> response, Page pageObjects){
        if(pageObjects == null ) {
            return response;
        }
        response.put("currentPage", pageObjects.getNumber());
        response.put("totalItems", pageObjects.getTotalElements());
        response.put("totalPages", pageObjects.getTotalPages());
        return response;
    }
}