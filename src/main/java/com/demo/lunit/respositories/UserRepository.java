package com.demo.lunit.respositories;

import com.demo.lunit.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = :username")
    Page<User> findAllUsersWithUsername(@Param("username") String username, Pageable pageable);
    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findUserByUsername(@Param("username") String username);
}
