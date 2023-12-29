package com.demo.lunit.controllers;

import com.demo.lunit.entities.Slide;
import com.demo.lunit.entities.User;
import com.demo.lunit.services.SlideService;
import com.demo.lunit.services.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SlideService slideService;

    @GetMapping("/users")
    public ResponseEntity<Map<String, Object>> getAllUsers(@RequestParam(required = false) String username,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "3") int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            List<User> users = null;
            Page<User> pageUsers;
            if (StringUtils.isNoneEmpty(username)) {
                pageUsers = userService.findAllUsersWithUsername(username, paging);
            } else {
                pageUsers = userService.findAllUser(paging);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("currentPage", pageUsers.getNumber());
            response.put("totalItems", pageUsers.getTotalElements());
            response.put("totalPages", pageUsers.getTotalPages());
            if (pageUsers != null) {
                users = pageUsers.getContent();
                response.put("users", users);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findOneUser(@PathVariable Long id) {
        Optional<User> userData = userService.findById(id);

        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
