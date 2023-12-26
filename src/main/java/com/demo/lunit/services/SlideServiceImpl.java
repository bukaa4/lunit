package com.demo.lunit.services;

import com.demo.lunit.entities.Slide;
import com.demo.lunit.respositories.SlideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SlideServiceImpl implements  SlideService {

    @Autowired
    private SlideRepository slideRepository;

    @Override
    public List<Slide> findAllSlide() { //public Iterable<Slide> findAllSlides() {
        return this.slideRepository.findAll();
    }

    @Override
    public Optional<Slide> findById(Long id) {
        return this.slideRepository.findById(id);
    }

    @Override
    public Slide insert(Slide slide) {
        return this.slideRepository.save(slide);
    }

    @Override
    public List<Slide> insertAll(List<Slide> slides) {
        return this.slideRepository.saveAll(slides);
    }

    @Override
    public void deleteById(Long id) {
        this.slideRepository.deleteById(id);
    }

    @Override
    public Slide update(Slide slide) {
        return this.slideRepository.save(slide);
    }
}
