package com.demo.lunit.controllers;

import com.demo.lunit.entities.Grid;
import com.demo.lunit.services.GridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class GridController {

    @Autowired
    private GridService gridService;

    @GetMapping("users/{userId}/grids")
    public ResponseEntity<Map<String, Object>> findAllGrids(@PathVariable Long userId,
                                                            @RequestParam(required = false) Long slideId,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "3") int size) {
        Page<Grid> pageGrids = null;
        //TODO: check userId
        try {
            Pageable paging = PageRequest.of(page, size);
            List<Grid> grids = null;
            if(slideId != null ){
                pageGrids = gridService.findAllGridsByUserIdAndSlideId(userId, slideId, paging);
            } else {
                pageGrids = gridService.findAllGridsByUserId(userId, paging);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("currentPage", pageGrids.getNumber());
            response.put("totalItems", pageGrids.getTotalElements());
            response.put("totalPages", pageGrids.getTotalPages());
            if (!pageGrids.isEmpty()) {
                grids = pageGrids.getContent();
                response.put("grids", grids);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("users/{userId}/grids/{gridId}")
    public ResponseEntity<Grid> getOneGridOfUser(@PathVariable Long userId,
                                                @PathVariable Long gridId) {
        Optional<Grid> gridData = gridService.findSlideByUserIdAndGridId(userId, gridId);

		if (gridData.isPresent()) {
			return new ResponseEntity<>(gridData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }

    //Only for test purpose
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
