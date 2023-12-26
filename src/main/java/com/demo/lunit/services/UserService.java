package com.demo.lunit.services;

import com.demo.lunit.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUser();
    Optional<User> findById(Long id);
    User insert(User user);
    List<User> insertAll(List<User> users);
    void deleteById(Long id);
    User update(User user);
}
