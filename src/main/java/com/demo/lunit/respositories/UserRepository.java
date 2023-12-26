package com.demo.lunit.respositories;

import com.demo.lunit.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
