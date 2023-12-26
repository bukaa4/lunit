package com.demo.lunit.services;

import com.demo.lunit.entities.Grid;

import java.util.List;
import java.util.Optional;

public interface GridService {
    List<Grid> findAllGrid();
    Optional<Grid> findById(Long id);
    Grid insert(Grid grid);
    List<Grid> insertAll(List<Grid> grids);
    void deleteById(Long id);
    Grid update(Grid grid);
}
