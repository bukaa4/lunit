package com.demo.lunit.services;

import com.demo.lunit.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.OffsetTime;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Page<User> findAllUser(Pageable pageable);
    Optional<User> findById(Long id);
    User insert(User user);
    List<User> insertAll(List<User> users);
    User update(User user);
    Optional<User> findUserByUsername(String username);
}
