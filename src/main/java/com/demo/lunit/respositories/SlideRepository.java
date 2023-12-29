package com.demo.lunit.respositories;

import com.demo.lunit.entities.Slide;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SlideRepository extends JpaRepository<Slide, Long> {
    @Query("SELECT s FROM Slide s WHERE s.userId = :userId")
    Page<Slide> findAllSlidesByUserId(@Param("userId") Long userId, Pageable pageable);
    @Query("SELECT s FROM Slide s WHERE s.id = :slideId and s.userId = :userId")
    Optional<Slide> findSlideByUserIdAndSlideId(@Param("userId") Long userId, @Param("slideId") Long slideId);
    //findCompletedSlideByUserIdAndSlideId
    @Query("SELECT s FROM Slide s WHERE s.status != Status.NONE and s.userId = :userId")
    Page<Slide> findCompletedSlideByUserIdAndSlideId(@Param("userId") Long userId, Pageable pageable);

}
