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

    @GetMapping("/grids")
    public ResponseEntity<Map<String, Object>> findAllGrids(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "3") int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            List<Grid> grids = null;
            Page<Grid> pageGrids = gridService.findAllGrid(paging);

            Map<String, Object> response = new HashMap<>();
            if (pageGrids != null) {
                grids = pageGrids.getContent();
                response.put("currentPage", pageGrids.getNumber());
                response.put("totalItems", pageGrids.getTotalElements());
                response.put("totalPages", pageGrids.getTotalPages());
            }
            response.put("grids", grids);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/grids/{id}")
    public ResponseEntity<Grid> findOneGrid(@PathVariable Long id) {
        Optional<Grid> gridData = gridService.findById(id);

		if (gridData.isPresent()) {
			return new ResponseEntity<>(gridData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }

   /* @PostMapping("/grid")
    public Grid addOneGrid(@RequestBody Grid grid) {
        return gridService.insert(grid);
    }

    @PostMapping("/grids")
    public List<Grid> addBulkGrid(@RequestBody List<Grid> grids) {
        return gridService.insertAll(grids);
    }*/
}
