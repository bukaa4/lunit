package com.demo.lunit.services;

import com.demo.lunit.entities.User;
import com.demo.lunit.exceptions.DbException;
import com.demo.lunit.exceptions.UserNotFoundException;
import com.demo.lunit.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements  UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> findAllUser(Pageable pageable) {
        try {
            Page<User> userPages = this.userRepository.findAll(pageable);
            if(userPages.getContent().isEmpty()){
                throw new UserNotFoundException("User not found.");
            } else {
                return userPages;
            }
        } catch (DbException e) {
            throw new DbException("Database error: " + e);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            Optional<User> user = this.userRepository.findById(id);
            if(user.isPresent()){
                return user;
            } else {
                throw new UserNotFoundException("User not found.");
            }
        } catch (DbException e) {
            throw new DbException("Database error: " + e);
        }
    }

    @Override
    public User insert(User user) {
        try {
            return this.userRepository.save(user);
        } catch (DbException e) {
            throw new DbException("Database error: " + e);
        }
    }

    @Override
    public List<User> insertAll(List<User> users) {
        try {
            return this.userRepository.saveAll(users);
        } catch (DbException e) {
            throw new DbException("Database error: " + e);
        }
    }

    @Override
    public User update(User user) {
        try {
            return this.userRepository.save(user);
        } catch (DbException e) {
            throw new DbException("Database error: " + e);
        }
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        try {
            Optional<User> user = this.userRepository.findUserByUsername(username);
            if(user.isPresent()){
                return user;
            } else {
                throw new UserNotFoundException("User not found.");
            }
        } catch (DbException e) {
            throw new DbException("Database error: " + e);
        }
    }
}
