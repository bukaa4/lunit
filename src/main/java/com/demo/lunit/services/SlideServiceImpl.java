package com.demo.lunit.services;

import com.demo.lunit.entities.Slide;
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
        return this.slideRepository.findAll(pageable);
    }

    @Override
    public Page<Slide> findAllSlideByUserId(Long userId, Pageable pageable) {
        return this.slideRepository.findAllSlidesByUserId(userId, pageable);
    }

    @Override
    public Page<Slide> findCompletedSlideByUserIdAndSlideId(Long userId, Pageable pageable) {
        return this.slideRepository.findCompletedSlideByUserIdAndSlideId(userId, pageable);
    }

    @Override
    public Optional<Slide> findSlideByUserIdAndSlideId(Long userId, Long slideId) {
        return this.slideRepository.findSlideByUserIdAndSlideId(userId, slideId);
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

        /* Optional<Slide> slideFromDb = slideRepository.findById(slide.getId());

        slideFromDb.get().setName(slide.getName());
        slideFromDb.get().setIsProcessed(slide.getIsProcessed());
        slideFromDb.get().setStatus(slide.getStatus());
        slideFromDb.get().setDecision(slide.getDecision());
        slideFromDb.get().setScore(slide.getScore());
        slideFromDb.get().setDescription(slide.getDescription());
        slideFromDb.get().setFormat(slide.getFormat());
        slideFromDb.get().setLicense(slide.getLicense());
        slideFromDb.get().setSize(slide.getSize());
        slideFromDb.get().setSha256(slide.getSha256());
        slideFromDb.get().setUserId(slide.getUserId()); */
    }
}
