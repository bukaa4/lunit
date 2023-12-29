package com.demo.lunit.controllers;

import com.demo.lunit.clients.AiClient;
import com.demo.lunit.entities.Grid;
import com.demo.lunit.entities.Slide;
import com.demo.lunit.entities.Status;
import com.demo.lunit.services.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

@RestController
public class SlideController {

    @Autowired
    private SlideService slideService;

    @Autowired
    private AiClient aiClient;

    @GetMapping(value = {"/users/{userId}/slides"})
    public ResponseEntity<Map<String, Object>> getAllSlidesOfUser(@PathVariable Long userId,
                                                                 @RequestParam(required = false) Long slideId,
                                                                 @RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "3") int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            List<Slide> slides = null;
            Page<Slide> pageSlides = null;
            if (userId != null && slideId != null) {
                Optional<Slide> slide;
                slide = slideService.findSlideByUserIdAndSlideId(userId, slideId);
                if(slide.isPresent()){
                    slides = new ArrayList<>();
                    slides.add(slide.get());
                }

            } else if(userId != null) {
                pageSlides = slideService.findAllSlideByUserId(userId, paging);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("currentPage", pageSlides.getNumber());
            response.put("totalItems", pageSlides.getTotalElements());
            response.put("totalPages", pageSlides.getTotalPages());
            if (pageSlides != null) {
                slides = pageSlides.getContent();
                response.put("slides", slides);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{userId}/slides/getAnalysis")
    public ResponseEntity<Object> sendRequestToAIClient(@PathVariable Long userId,
                                                        @RequestBody List<Slide> slides) {
        try {
            List<Slide> result = aiClient.doAnalysis(slides, userId);

            if(result == null || result.size() == 0) {
            //if(true){
                return new ResponseEntity<>("AI analysis is failed.", HttpStatus.BAD_REQUEST);

            } else {
                //TODO:return slides instead of GRIDs.
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users/{userId}/slides/upload")
    public ResponseEntity<List<Slide>> uploadSlides(@PathVariable Long userId,
                                                    @RequestBody List<Slide> slides) {
        try {
            slides.forEach(slide -> {
                slide.setUserId(userId);
            });
            List<Slide> _slides = slideService.insertAll(slides);
            if (_slides.size() > 0) {
                return new ResponseEntity<>(_slides, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = {"/users/{userId}/slides/requestHistory"})
    public ResponseEntity<Map<String, Object>> getAllRequestedSlidesOfUser(@PathVariable Long userId,
                                                                  @RequestParam(required = false) Long slideId,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "3") int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            List<Slide> slides = null;
            Page<Slide> pageSlides = null;
            if(userId != null && slideId != null) {
                Optional<Slide> dbSlide = slideService.findSlideByUserIdAndSlideId(userId, slideId);
                if(dbSlide.isPresent() && dbSlide.get().getStatus() != Status.NONE){
                    slides.add(dbSlide.get());
                }
            }
            if(userId != null) {
                pageSlides = slideService.findCompletedSlideByUserIdAndSlideId(userId, paging);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("currentPage", pageSlides.getNumber());
            response.put("totalItems", pageSlides.getTotalElements());
            response.put("totalPages", pageSlides.getTotalPages());
            if (pageSlides != null) {
                slides = pageSlides.getContent();
                response.put("slides", slides);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
