package com.demo.lunit.services;

import com.demo.lunit.entities.Slide;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SlideService {
    Page<Slide> findAllSlide(Pageable pageable);

    Page<Slide> findAllSlideByUserId(Long userId, Pageable pageable);
    Page<Slide> findCompletedSlideByUserIdAndSlideId(Long userId, Pageable pageable);
    Optional<Slide> findSlideByUserIdAndSlideId(Long userId, Long slideId);
    Slide insert(Slide slide);
    List<Slide> insertAll(List<Slide> slides);
    void deleteById(Long id);
    Slide update(Slide slide);
}
