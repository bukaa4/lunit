package com.demo.lunit.controllers;

import com.demo.lunit.entities.Slide;
import com.demo.lunit.services.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SlideController {

    @Autowired
    private SlideService slideService;

    @GetMapping("/slides")
    public Iterable<Slide> findAllSlides() {
        return slideService.findAllSlide();
    }

    @PostMapping("/slide")
    public Slide addOneSlide(@RequestBody Slide slide) {
        return slideService.insert(slide);
    }

    @PostMapping("/slides")
    public List<Slide> addBulkSlide(@RequestBody List<Slide> slides) {
        return slideService.insertAll(slides);
    }
}
