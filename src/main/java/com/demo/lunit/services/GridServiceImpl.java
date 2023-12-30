package com.demo.lunit.services;

import com.demo.lunit.entities.Grid;
import com.demo.lunit.exceptions.DbException;
import com.demo.lunit.exceptions.GridNotFoundException;
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
    public Page<Grid> findAllGridsByUserId(Long userId, Pageable pageable) {
        try {
            Page<Grid> gridPages = this.gridRepository.findAllGridsByUserId(userId, pageable);
            if(gridPages.getContent().isEmpty()){
                throw new GridNotFoundException("Grid not found.");
            } else {
                return gridPages;
            }
        } catch (DbException e) {
            throw new DbException("Database error: " + e);
        }
    }

    @Override
    public Page<Grid> findAllGridsByUserIdAndSlideId(Long userId, Long slideId, Pageable pageable) {
        try {
            Page<Grid> gridPages = this.gridRepository.findAllGridsByUserIdAndSlideId(userId, slideId, pageable);
            if(gridPages.getContent().isEmpty()){
                throw new GridNotFoundException("Grid not found.");
            } else {
                return gridPages;
            }
        } catch (DbException e) {
            throw new DbException("Database error: " + e);
        }
    }

    @Override
    public Optional<Grid> findSlideByUserIdAndGridId(Long userId, Long gridId) {
        try {
            Optional<Grid> grid = this.gridRepository.findSlideByUserIdAndGridId(userId, gridId);
            if(grid.isPresent()){
                return grid;
            } else {
                throw new GridNotFoundException("Grid not found.");
            }
        } catch (DbException e) {
            throw new DbException("Database error: " + e);
        }
    }

    @Override
    public Grid insert(Grid grid) {
        try {
            Grid _grid = this.gridRepository.save(grid);
            if(_grid != null) {
                return _grid;
            } else {
                throw new GridNotFoundException("Grid not found.");
            }
        } catch (DbException e) {
            throw new DbException("Database error: " + e);
        }
    }

    @Override
    public List<Grid> insertAll(List<Grid> grids) {
        try {
            List<Grid> _grids = this.gridRepository.saveAll(grids);
            if(_grids.size() > 0) {
                return _grids;
            } else {
                throw new GridNotFoundException("Grid not found.");
            }
        } catch (DbException e) {
            throw new DbException("Database error: " + e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            this.gridRepository.deleteById(id);
        } catch (DbException e) {
            throw new DbException("Database error: " + e);
        }
    }

    @Override
    public Grid update(Grid grid) {
        try {
            Grid _grid = this.gridRepository.save(grid);
            if(_grid != null) {
                return _grid;
            } else {
                throw new GridNotFoundException("Grid not found.");
            }
        } catch (DbException e) {
            throw new DbException("Database error: " + e);
        }
    }

    public Page<Grid> findAllGrid(Pageable pageable){
        try {
            Page<Grid> gridPages = this.gridRepository.findAll(pageable);
            if(gridPages.getContent().isEmpty()){
                throw new GridNotFoundException("Grid not found.");
            } else {
                return gridPages;
            }
        } catch (DbException e) {
            throw new DbException("Database error: " + e);
        }
    }
}
