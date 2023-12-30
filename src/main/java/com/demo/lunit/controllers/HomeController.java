package com.demo.lunit.controllers;

import com.demo.lunit.LunitApplication;
import com.demo.lunit.entities.User;
import com.demo.lunit.exceptions.DbException;
import com.demo.lunit.exceptions.ResponseError;
import com.demo.lunit.exceptions.RestExceptionHandler;
import com.demo.lunit.exceptions.UserNotFoundException;
import com.demo.lunit.services.UserService;
import com.demo.lunit.validators.UserValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v1")
@Tag(name = "Home APIs")
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private RestExceptionHandler exceptionHandler;
    @Autowired
    private UserValidator validator;

    @Operation(
            summary = "Register a new user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = {
                                    @ExampleObject(value = """ 
                                                {
                                                  "name": "Mary J",
                                                  "username": "mary",
                                                  "password": "marypass",
                                                  "email": "mary@test.com",
                                                  "phone": "321"
                                                } 
                                            """)})
            ))
    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User user) {
        validator.validate(user);
        try {
            User _user = userService.insert(user);
            logger.info("INFO: User is inserted");
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            return exceptionHandler.handleUserNotFoundException();
        } catch (DbException dbException) {
            return exceptionHandler.handleDatabaseException();
        } catch (Exception ex) {
            return new ResponseEntity(new ResponseError(500, "Something is wrong."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    @Operation(
            summary = "Login by username and password",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = {
                                    @ExampleObject(value = """ 
                                                {
                                                  "username": "mary",
                                                  "password": "marypass"
                                                  } 
                                            """)})
            ))
    public ResponseEntity login(@RequestBody User loginUser) {
        if (StringUtils.isNoneEmpty(loginUser.getUsername())
                && StringUtils.isNoneEmpty(loginUser.getPassword())) {
            Optional<User> user = userService.findUserByUsername(loginUser.getUsername());
            try {
                if (loginUser.getPassword().equals(user.get().getPassword())) {
                    logger.info("INFO: User is successfully logged in");
                    return ResponseEntity.status(HttpStatus.OK)
                            .body("You are successfully logged in.");
                } else {
                    logger.info("INFO: User password does not match.");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("Password does not match!");
                }
            } catch (UserNotFoundException e) {
                return exceptionHandler.handleUserNotFoundException();
            } catch (DbException dbException) {
                return exceptionHandler.handleDatabaseException();
            } catch (Exception ex) {
                return new ResponseEntity(new ResponseError(500, "Something is wrong."), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return exceptionHandler.handleInvalidRequestException();
        }
    }
}
