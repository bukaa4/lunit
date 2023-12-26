package com.demo.lunit.controllers;

import com.demo.lunit.entities.Grid;
import com.demo.lunit.services.GridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GridController {

    @Autowired
    private GridService gridService;

    @GetMapping("/grids")
    public Iterable<Grid> findAllGrids() {
        return gridService.findAllGrid();
    }

    @PostMapping("/grid")
    public Grid addOneGrid(@RequestBody Grid grid) {
        return gridService.insert(grid);
    }

    @PostMapping("/grids")
    public List<Grid> addBulkGrid(@RequestBody List<Grid> grids) {
        return gridService.insertAll(grids);
    }
}
