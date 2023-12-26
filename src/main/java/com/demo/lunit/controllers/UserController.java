package com.demo.lunit.controllers;

import com.demo.lunit.entities.User;
import com.demo.lunit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Iterable<User> findAllUsers() {
        return userService.findAllUser();
    }

    @PostMapping("/user")
    public User addOneUser(@RequestBody User user) {
        return userService.insert(user);
    }

    @PostMapping("/users")
    public List<User> addBulkUser(@RequestBody List<User> users) {
       return userService.insertAll(users);
    }
}
