package com.demo.lunit.services;

import com.demo.lunit.entities.User;
import com.demo.lunit.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements  UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAllUser() { //public Iterable<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public User insert(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public List<User> insertAll(List<User> users) {
        return this.userRepository.saveAll(users);
    }

    @Override
    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public User update(User user) {
        return this.userRepository.save(user);
    }
}
