package com.demo.lunit.services;

import com.demo.lunit.entities.Slide;
import java.util.List;
import java.util.Optional;

public interface SlideService {
    List<Slide> findAllSlide();
    Optional<Slide> findById(Long id);
    Slide insert(Slide slide);
    List<Slide> insertAll(List<Slide> slides);
    void deleteById(Long id);
    Slide update(Slide slide);
}
