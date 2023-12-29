package com.demo.lunit.respositories;

import com.demo.lunit.entities.Grid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GridRepository extends JpaRepository<Grid, Long> {
    @Query("SELECT g FROM Grid g WHERE g.userId = :userId")
    Page<Grid> findAllGridsByUserId(@Param("userId") Long userId, Pageable pageable);
    @Query("SELECT g FROM Grid g WHERE g.userId = :userId and g.slideId = :slideId")
    Page<Grid> findAllGridsByUserIdAndSlideId(@Param("userId") Long userId,
                                    @Param("slideId") Long slideId, Pageable pageable);

    @Query("SELECT g FROM Grid g WHERE g.userId = :userId and g.id = :gridId")
    Optional<Grid> findSlideByUserIdAndGridId(@Param("userId") Long userId,
                                              @Param("gridId") Long gridId);
}
