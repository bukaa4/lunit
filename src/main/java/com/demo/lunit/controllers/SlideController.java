package com.demo.lunit.controllers;

import com.demo.lunit.clients.AiClient;
import com.demo.lunit.entities.Slide;
import com.demo.lunit.entities.Status;
import com.demo.lunit.entities.User;
import com.demo.lunit.exceptions.*;
import com.demo.lunit.services.SlideService;
import com.demo.lunit.services.UserService;
import com.demo.lunit.utils.PagingUtil;
import com.demo.lunit.validators.SlideValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/v1")
@Tag(name = "Slide related APIs")
public class SlideController {
    private static final Logger logger = LoggerFactory.getLogger(SlideController.class);
    @Autowired
    private SlideService slideService;
    @Autowired
    private UserService userService;
    @Autowired
    private AiClient aiClient;
    @Autowired
    private SlideValidator validator;
    @Autowired
    private RestExceptionHandler exceptionHandler;
    @GetMapping(value = {"/users/{userId}/slides"})
    @Operation(summary = "Get all existing slides of one user")
    public ResponseEntity getAllSlidesOfUser(@PathVariable Long userId,
                                             @RequestParam(required = false) Long slideId,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "3") int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            List<Slide> slides = new ArrayList<>();
            ;
            Page<Slide> pageSlides = null;

            if (userId != null && slideId != null) {
                logger.info("INFO: Find all slides by userId and slideId.");
                Optional<Slide> slide;
                slide = slideService.findSlideByUserIdAndSlideId(userId, slideId);
                if (slide.isPresent()) {
                    slides.add(slide.get());
                }
            } else {
                logger.info("INFO: Find all slides by userId only userId.");
                pageSlides = slideService.findAllSlideByUserId(userId, paging);
                slides = pageSlides.getContent();
            }

            Map<String, Object> response = new HashMap<>();
            response = PagingUtil.buildPagingInfo(response, pageSlides);
            response.put("slides", slides);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (SlideNotFoundException e) {
            return exceptionHandler.handleSlideNotFoundException();
        } catch (DbException dbException) {
            return exceptionHandler.handleDatabaseException();
        } catch (Exception ex) {
            return new ResponseEntity(new ResponseError(500, "Something is wrong."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = {"/users/{userId}/slides/requestHistory"})
    @Operation(summary = "Get a list of all requested slides by userId")
    public ResponseEntity getAllRequestedSlidesOfUser(@PathVariable Long userId,
                                                      @RequestParam(required = false) Long slideId,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "3") int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            List<Slide> slides = new ArrayList<>();
            Page<Slide> pageSlides = null;
            if (userId != null && slideId != null) {
                logger.info("INFO: Find history slides by userId only userId.");
                Optional<Slide> dbSlide = slideService.findSlideByUserIdAndSlideId(userId, slideId);
                if (dbSlide.isPresent() && dbSlide.get().getStatus() != Status.NONE) {
                    slides.add(dbSlide.get());
                }
            } else {
                logger.info("INFO: Find history slides by only userId.");
                pageSlides = slideService.findCompletedSlideByUserIdAndSlideId(userId, paging);
                slides = pageSlides.getContent();
            }

            Map<String, Object> response = new HashMap<>();
            response = PagingUtil.buildPagingInfo(response, pageSlides);
            response.put("slides", slides);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (SlideNotFoundException e) {
            return exceptionHandler.handleSlideNotFoundException();
        } catch (DbException dbException) {
            return exceptionHandler.handleDatabaseException();
        } catch (Exception ex) {
            return new ResponseEntity(new ResponseError(500, "Something is wrong."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users/{userId}/slides/upload")
    @Operation(
            summary = "Upload(create) a new slide and store in DB",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = {
                                    @ExampleObject(value = """ 
                                                [
                                                  {
                                                    "name": "CMU-1-Small-Region.svs",
                                                    "isProcessed": false,
                                                    "status": "NONE",
                                                    "decision": null,
                                                    "score": null,
                                                    "description": "Exported region from CMU-1.svs",
                                                    "format": "Aperio SVS",
                                                    "license": "CC0-1.0",
                                                    "size": 1938955,
                                                    "sha256": "ed92d5a9f2e86df6"
                                                  }
                                                ]
                                            """)})
            ))
    public ResponseEntity uploadSlides(@PathVariable Long userId,
                                       @RequestBody List<Slide> slides) {
        Optional<User> userData;
        try {
            userData = userService.findById(userId);
        } catch (UserNotFoundException e) {
            logger.info("INFO: User not found.");
            return exceptionHandler.handleUserNotFoundException();
        }

        if(userData.isPresent()){
            slides.forEach(slide -> {
                validator.validate(slide);
                slide.setUserId(userId);
            });

            try {
                List<Slide> _slides = slideService.insertAll(slides);
                return new ResponseEntity<>(_slides, HttpStatus.OK);
            } catch (SlideNotFoundException e) {
                return exceptionHandler.handleSlideNotFoundException();
            } catch (DbException dbException) {
                return exceptionHandler.handleDatabaseException();
            } catch (Exception ex) {
                return new ResponseEntity(new ResponseError(500, "Something is wrong."), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return exceptionHandler.handleUserNotFoundException();
        }
    }

    @PutMapping("/users/{userId}/slides/getAnalysis")
    @Operation(
            summary = "Send request to do analysis on all selected slides from AI client",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = {
                                    @ExampleObject(value = """ 
                                                [
                                                  {
                                                    "id": 4,
                                                    "name": "CMU-1-Small-Region.svs",
                                                    "isProcessed": false,
                                                    "status": "NONE",
                                                    "decision": null,
                                                    "score": null,
                                                    "description": "Exported region from CMU-1.svs",
                                                    "format": "Aperio SVS",
                                                    "license": "CC0-1.0",
                                                    "size": 1938955,
                                                    "sha256": "ed92d5a9f2e86df6"
                                                  }
                                                ]
                                            """)})
            ))
    public ResponseEntity sendRequestToAIClient(@PathVariable Long userId,
                                                @RequestBody List<Slide> slides) {
        try {
            slides.forEach(slide -> {
                if (slide.getIsProcessed()) {
                    logger.info("INFO: AI client process is failed.");
                    throw new AIClientException("AI client is failed");
                }
            });
            List<Slide> result = aiClient.doAnalysis(slides, userId);
            logger.info("INFO: AI client process is succeeded.");
            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (AIClientException e) {
            return exceptionHandler.handleAiClientException();
        } catch (GridNotFoundException e) {
            return exceptionHandler.handleGridNotFoundException();
        } catch (SlideNotFoundException e) {
            return exceptionHandler.handleSlideNotFoundException();
        } catch (DbException dbException) {
            return exceptionHandler.handleDatabaseException();
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
