package com.demo.lunit.services;

import com.demo.lunit.entities.Slide;
import com.demo.lunit.exceptions.DbException;
import com.demo.lunit.exceptions.SlideNotFoundException;
import com.demo.lunit.respositories.SlideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SlideServiceImpl implements  SlideService {

    @Autowired
    private SlideRepository slideRepository;

    @Override
    public Page<Slide> findAllSlide(Pageable pageable) {
        try {
            Page<Slide> slidePages = this.slideRepository.findAll(pageable);
            if(slidePages.getContent().isEmpty()){
                throw new SlideNotFoundException("Slide not found.");
            } else {
                return slidePages;
            }
        } catch (DbException e) {
            throw new DbException("Database error: " + e);
        }
    }

    @Override
    public Page<Slide> findAllSlideByUserId(Long userId, Pageable pageable) {
        try {
            Page<Slide> slidePages = this.slideRepository.findAllSlidesByUserId(userId, pageable);
            if(slidePages.getContent().isEmpty()){
                throw new SlideNotFoundException("Slide not found.");
            } else {
                return slidePages;
            }
        } catch (DbException e) {
            throw new DbException("Database error: " + e);
        }
    }

    @Override
    public Page<Slide> findCompletedSlideByUserIdAndSlideId(Long userId, Pageable pageable) {
        try {
            Page<Slide> slidePages = this.slideRepository.findCompletedSlideByUserIdAndSlideId(userId, pageable);
            if(slidePages.getContent().isEmpty()){
                throw new SlideNotFoundException("Slide not found.");
            } else {
                return slidePages;
            }
        } catch (DbException e) {
            throw new DbException("Database error: " + e);
        }
    }

    @Override
    public Optional<Slide> findSlideByUserIdAndSlideId(Long userId, Long slideId) {
        try {
            Optional<Slide> slide = this.slideRepository.findSlideByUserIdAndSlideId(userId, slideId);
            if(slide.isPresent()){
                return slide;
            } else {
                throw new SlideNotFoundException("Slide not found.");
            }
        } catch (DbException e) {
            throw new DbException("Database error: " + e);
        }
    }

    @Override
    public Slide insert(Slide slide) {
        try {
            return this.slideRepository.save(slide);
        } catch (DbException e) {
            throw new DbException("Database error: " + e);
        }
    }

    @Override
    public List<Slide> insertAll(List<Slide> slides) {
        try {
            List<Slide> _slides = this.slideRepository.saveAll(slides);
            if(_slides.size() > 0) {
                return _slides;
            } else {
                throw new SlideNotFoundException("Slide not found.");
            }
        } catch (DbException e) {
            throw new DbException("Database error: " + e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            this.slideRepository.deleteById(id);
        } catch (DbException e) {
            throw new DbException("Database error: " + e);
        }
    }

    @Override
    public Slide update(Slide slide) {
        try {
            return this.slideRepository.save(slide);
        } catch (DbException e) {
            throw new DbException("Database error: " + e);
        }
    }
}
