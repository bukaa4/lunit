package com.demo.lunit.services;

import com.demo.lunit.entities.Grid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface GridService {
    Page<Grid> findAllGrid(Pageable pageable);
    Optional<Grid> findById(Long id);
    Grid insert(Grid grid);
    List<Grid> insertAll(List<Grid> grids);
    void deleteById(Long id);
    Grid update(Grid grid);
}
