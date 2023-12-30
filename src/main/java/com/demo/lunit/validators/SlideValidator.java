package com.demo.lunit.validators;

import com.demo.lunit.entities.Slide;
import com.demo.lunit.entities.Status;
import com.demo.lunit.exceptions.InvalidRequestException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class SlideValidator {
    public void validate(Slide slide) {
        if(slide == null) {
            throw new InvalidRequestException("Slide is null.");
        }
        if(StringUtils.isEmpty(slide.getName()))  {
            throw new InvalidRequestException("name field is invalid.");
        }
        if(slide.getIsProcessed() == null)  {
            throw new InvalidRequestException("isProcessed field is invalid.");
        }
        if(StringUtils.isEmpty(slide.getDescription()))  {
            throw new InvalidRequestException("description field is invalid.");
        }
        if(StringUtils.isEmpty(slide.getFormat()))  {
            throw new InvalidRequestException("format field is invalid.");
        }
        if(StringUtils.isEmpty(slide.getLicense()))  {
            throw new InvalidRequestException("license field is invalid.");
        }
        if(slide.getSize() == null)  {
            throw new InvalidRequestException("size field is invalid.");
        }
        if(StringUtils.isEmpty(slide.getSha256()))  {
            throw new InvalidRequestException("sha256 field is invalid.");
        }
    }
}
