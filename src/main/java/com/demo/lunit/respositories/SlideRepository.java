package com.demo.lunit.respositories;

import com.demo.lunit.entities.Slide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlideRepository extends JpaRepository<Slide, Long> {
}
