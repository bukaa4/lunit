package com.demo.lunit.validators;

import com.demo.lunit.entities.User;
import com.demo.lunit.exceptions.InvalidRequestException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {
   public void validate(User user) {
       if(user == null) {
           throw new InvalidRequestException("User is null.");
       }
       if(StringUtils.isEmpty(user.getName()))  {
          throw new InvalidRequestException("name field is invalid.");
       }
       if(StringUtils.isEmpty(user.getUsername()))  {
           throw new InvalidRequestException("username field is invalid.");
       }
       if(StringUtils.isEmpty(user.getPassword()))  {
           throw new InvalidRequestException("password field is invalid.");
       }
       if(StringUtils.isEmpty(user.getEmail()))  {
           throw new InvalidRequestException("email field is invalid.");
       }
       if(StringUtils.isEmpty(user.getPhone()))  {
           throw new InvalidRequestException("phone field is invalid.");
       }
   }
}
