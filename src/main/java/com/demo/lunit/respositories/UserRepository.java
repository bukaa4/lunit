package com.demo.lunit.respositories;

import com.demo.lunit.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

   /* @Query("SELECT u FROM User u WHERE u.email = ?1")
    Page<User> findAllUsersWithEmail(String email, Pageable pageable);
*/
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Page<User> findAllUsersWithEmail(@Param("email") String email, Pageable pageable);
}
