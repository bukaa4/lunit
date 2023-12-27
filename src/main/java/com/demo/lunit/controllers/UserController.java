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

import java.util.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SlideService slideService;

    @GetMapping("/users")
    public ResponseEntity<Map<String, Object>> findAllUsers(@RequestParam(required = false) String email,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "3") int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            List<User> users = null;
            Page<User> pageUsers = null;
            if (StringUtils.isNoneEmpty(email)) {
                pageUsers = userService.findAllUsersWithEmail(email, paging);
            } else {
                pageUsers = userService.findAllUser(paging);
            }
            Map<String, Object> response = new HashMap<>();
            if (pageUsers != null) {
                users = pageUsers.getContent();
                response.put("currentPage", pageUsers.getNumber());
                response.put("totalItems", pageUsers.getTotalElements());
                response.put("totalPages", pageUsers.getTotalPages());
            }
            response.put("users", users);

            return new ResponseEntity<>(response, HttpStatus.OK);
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

    @GetMapping(value = {"/users/{userId}/slides", "/users/{userId}/slides/{slideId}"})
    public ResponseEntity<Map<String, Object>> findOneUserSlides(@PathVariable Long userId,
                                                                 @PathVariable(required = false) Long slideId,
                                                                 @RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "3") int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            List<Slide> slides = null;
            Page<Slide> pageSlides = null;
            if (userId != null && slideId != null) {
                Optional<Slide> slide;
                slide = slideService.findSlideByUserIdAndSlideId(userId, slideId);
                if(slide.isPresent()){
                    slides = new ArrayList<>();
                    slides.add(slide.get());
                }

            } else if(userId != null) {
                pageSlides = slideService.findAllSlideByUserId(userId, paging);
            }

            Map<String, Object> response = new HashMap<>();
            if (pageSlides != null) {
                slides = pageSlides.getContent();
                response.put("currentPage", pageSlides.getNumber());
                response.put("totalItems", pageSlides.getTotalElements());
                response.put("totalPages", pageSlides.getTotalPages());
            }
            response.put("slides", slides);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<User> addOneUser(@RequestBody User user) {
        try {
            User _user = userService.insert(user);
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //for testPurpose
    @PostMapping("/users")
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
    }
}
