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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class SlideController {

    @Autowired
    private SlideService slideService;

    @Autowired
    private AiClient aiClient;

    @GetMapping("/slides")
    public ResponseEntity<Map<String, Object>> findAllSlides(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "3") int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            List<Slide> slides = null;
            Page<Slide> pageSlides = slideService.findAllSlide(paging);

            Map<String, Object> response = new HashMap<>();
            if (pageSlides != null) {
                slides = pageSlides.getContent();
                response.put("currentPage", pageSlides.getNumber());
                response.put("totalItems", pageSlides.getTotalElements());
                response.put("totalPages", pageSlides.getTotalPages());
            }
            response.put("slides", slides);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/slides/{id}")
    public ResponseEntity<Slide> findOneSlide(@PathVariable Long id) {
        Optional<Slide> slideData = slideService.findById(id);

		if (slideData.isPresent()) {
			return new ResponseEntity<>(slideData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
    @PostMapping("/slide")
    public ResponseEntity<Slide> addOneSlide(@RequestBody Slide slide) {
        try {
            Slide _slide = slideService.insert(slide);
            return new ResponseEntity<>(_slide, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/slide/getAnalysis")
    public ResponseEntity<Object> sendRequestToAIClient(@RequestBody Slide slide) {
        try {
            List<Grid> grids = aiClient.doAnalysis(slide);
            //if(grids == null) {
            if(true){
                slide.setDecision(0);
                slide.setScore(0.0);
                slide.setStatus(Status.FAILED);
                return new ResponseEntity<>("ERROR: AI analysis is failed.", HttpStatus.BAD_REQUEST);

            } else {
                slide.setDecision(1);
                slide.setScore(86.0);
                slide.setStatus(Status.COMPLETED);
                //slide.setGrids(grids);
            }
            slide = slideService.update(slide);
            return new ResponseEntity<>(slide, HttpStatus.OK);

        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/slides")
    public ResponseEntity<List<Slide>> addBulkSlide(@RequestBody List<Slide> slides) {
        try {
            List<Slide> _slides = slideService.insertAll(slides);
            if (_slides.size() > 0) {
                return new ResponseEntity<>(_slides, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
