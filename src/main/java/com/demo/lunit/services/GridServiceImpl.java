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
    public Page<Grid> findAllGridsByUserId(Long userId, Pageable pageable) { //public Iterable<Grid> findAllGrids() {
        return this.gridRepository.findAllGridsByUserId(userId, pageable);
    }

    @Override
    public Page<Grid> findAllGridsByUserIdAndSlideId(Long userId, Long slideId, Pageable pageable) {
        return this.gridRepository.findAllGridsByUserIdAndSlideId(userId, slideId, pageable);
    }

    @Override
    public Optional<Grid> findSlideByUserIdAndGridId(Long userId, Long gridId) {
        return this.gridRepository.findSlideByUserIdAndGridId(userId, gridId);
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

    public Page<Grid> findAllGrid(Pageable pageable){
        return this.gridRepository.findAll(pageable);
    }
}
