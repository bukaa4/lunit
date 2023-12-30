package com.demo.lunit.controllers;

import com.demo.lunit.entities.Grid;
import com.demo.lunit.exceptions.DbException;
import com.demo.lunit.exceptions.GridNotFoundException;
import com.demo.lunit.exceptions.ResponseError;
import com.demo.lunit.exceptions.RestExceptionHandler;
import com.demo.lunit.services.GridService;
import com.demo.lunit.utils.PagingUtil;
import io.swagger.v3.oas.annotations.Operation;
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
@Tag(name = "Grid related APIs")
public class GridController {
    private static final Logger logger = LoggerFactory.getLogger(GridController.class);
    @Autowired
    private GridService gridService;
    @Autowired
    private RestExceptionHandler exceptionHandler;

    @GetMapping("/users/{userId}/grids")
    @Operation(summary = "Get all existing grids of one user")
    public ResponseEntity findAllGrids(@PathVariable Long userId,
                                       @RequestParam(required = false) Long slideId,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "3") int size) {
        try {
            Page<Grid> pageGrids = null;
            Pageable paging = PageRequest.of(page, size);
            List<Grid> grids = new ArrayList<>();
            if (slideId != null) {
                logger.info("INFO: Find all grids by slideId and userId");
                pageGrids = gridService.findAllGridsByUserIdAndSlideId(userId, slideId, paging);
            } else {
                logger.info("INFO: Find all grids by userId.");
                pageGrids = gridService.findAllGridsByUserId(userId, paging);
            }
            grids = pageGrids.getContent();
            Map<String, Object> response = new HashMap<>();
            response = PagingUtil.buildPagingInfo(response, pageGrids);
            response.put("grids", grids);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (GridNotFoundException e) {
            return exceptionHandler.handleGridNotFoundException();
        } catch (DbException dbException) {
            return exceptionHandler.handleDatabaseException();
        } catch (Exception ex) {
            return new ResponseEntity(new ResponseError(500, "Something is wrong."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{userId}/grids/{gridId}")
    @Operation(summary = "Get an one specific grid of one user")
    public ResponseEntity getOneGridOfUser(@PathVariable Long userId,
                                           @PathVariable Long gridId) {
        try {
            Optional<Grid> gridData = gridService.findSlideByUserIdAndGridId(userId, gridId);
            logger.info("INFO: Found a gird by userId and gridId.");
            return new ResponseEntity<>(gridData.get(), HttpStatus.OK);
        } catch (GridNotFoundException e) {
            return exceptionHandler.handleGridNotFoundException();
        } catch (DbException dbException) {
            return exceptionHandler.handleDatabaseException();
        } catch (Exception ex) {
            return new ResponseEntity(new ResponseError(500, "Something is wrong."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Only for test purpose - get All grids of all users
   /* @GetMapping("/grids")
    public ResponseEntity<Object> getAllGridsForTest(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Grid> gridData = gridService.findAllGrid(paging);
            List<Grid> grids = null;
            //gridData.getContent().forEach(System.out::println);

            Map<String, Object> response = new HashMap<>();
            response.put("currentPage", gridData.getNumber());
            response.put("totalItems", gridData.getTotalElements());
            response.put("totalPages", gridData.getTotalPages());

            if (!gridData.getContent().isEmpty()) {
                grids = gridData.getContent();
                response.put("grids", grids);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
}
