package com.demo.lunit.services;

import com.demo.lunit.entities.Grid;
import com.demo.lunit.respositories.GridRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GridServiceImpl implements GridService {
    @Autowired
    private GridRepository gridRepository;

    @Override
    public Page<Grid> findAllGrid(Pageable pageable) { //public Iterable<Grid> findAllGrids() {
        return this.gridRepository.findAll(pageable);
    }

    @Override
    public Optional<Grid> findById(Long id) {
        return this.gridRepository.findById(id);
    }

    @Override
    public Grid insert(Grid grid) {
        return this.gridRepository.save(grid);
    }

    @Override
    public List<Grid> insertAll(List<Grid> grids) {
        return this.gridRepository.saveAll(grids);
    }

    @Override
    public void deleteById(Long id) {
        this.gridRepository.deleteById(id);
    }

    @Override
    public Grid update(Grid grid) {
        return this.gridRepository.save(grid);
    }
}
