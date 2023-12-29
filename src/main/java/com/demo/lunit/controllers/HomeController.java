package com.demo.lunit.controllers;

import com.demo.lunit.entities.User;
import com.demo.lunit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class HomeController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        //TODO: check request, validate
        try {
            User _user = userService.insert(user);
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginUser) {
        //TODO: check username or password is not empty
        Optional<User> user = userService.findUserByUsername(loginUser.getUsername());
        if(!user.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User does not exist");
        } else if(loginUser.getPassword().equals(user.get().getPassword())){
            return ResponseEntity.status(HttpStatus.OK)
                    .body("You are successfully logged in");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Password does NOT match!");
        }
    }
}
