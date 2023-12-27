package com.demo.lunit.services;

import com.demo.lunit.entities.User;
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
    public Page<User> findAllUser(Pageable pageable) { //public Iterable<User> findAllUsers() {
        return this.userRepository.findAll(pageable);
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

        //TODO:findONe and delet
        this.userRepository.deleteById(id);
    }

    @Override
    public User update(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public Page<User> findAllUsersWithEmail(String email, Pageable pageable) {
        return this.userRepository.findAllUsersWithEmail(email, pageable);
    }
}
