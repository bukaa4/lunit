package com.demo.lunit.controllers;

import com.demo.lunit.entities.User;
import com.demo.lunit.exceptions.DbException;
import com.demo.lunit.exceptions.ResponseError;
import com.demo.lunit.exceptions.RestExceptionHandler;
import com.demo.lunit.exceptions.UserNotFoundException;
import com.demo.lunit.services.SlideService;
import com.demo.lunit.services.UserService;
import com.demo.lunit.utils.PagingUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/v1/users")
@Tag(name="User APIs")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private SlideService slideService;
    @Autowired
    private RestExceptionHandler exceptionHandler;

    @GetMapping
    @Operation(summary = "Get list of all users and can find by username")
    public ResponseEntity getAllUsers(@RequestParam(required = false) String username,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "3") int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            List<User> users = new ArrayList<>();
            Page<User> pageUsers = null;
            if (StringUtils.isNoneEmpty(username)) {
                logger.info("INFO: Find user by username.");
                Optional<User> user = userService.findUserByUsername(username);
                users.add(user.get());
            } else {
                logger.info("INFO: Find all users.");
                pageUsers = userService.findAllUser(paging);
                users = pageUsers.getContent();
            }

            Map<String, Object> response = new HashMap<>();
            response = PagingUtil.buildPagingInfo(response, pageUsers);
            response.put("users", users);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return exceptionHandler.handleUserNotFoundException();
        } catch (DbException dbException) {
            return exceptionHandler.handleDatabaseException();
        } catch (Exception ex) {
            return new ResponseEntity(new ResponseError(500, "Something is wrong."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @Operation( summary = "Get an one specific user's info")
    public ResponseEntity findOneUser(@PathVariable Long id) {
        if(id > 0) {
            try {
                Optional<User> userData = userService.findById(id);
                logger.info("INFO: Found one user.");
                return new ResponseEntity<>(userData.get(), HttpStatus.OK);
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

    //Only for test purpose
    /*@PostMapping("/users")
    public ResponseEntity<List<User>> addBulkUser(@RequestBody List<User> users) {
        try {
            List<User> _users = userService.insertAll(users);
            if (_users.size() > 0) {
                return new ResponseEntity<>(_users, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
}
