package com.demo.lunit.clients;

import com.demo.lunit.entities.Grid;
import com.demo.lunit.entities.Slide;
import com.demo.lunit.entities.Status;
import com.demo.lunit.exceptions.DbException;
import com.demo.lunit.exceptions.GridNotFoundException;
import com.demo.lunit.exceptions.InvalidRequestException;
import com.demo.lunit.exceptions.SlideNotFoundException;
import com.demo.lunit.services.GridService;
import com.demo.lunit.services.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class AiClient {

    @Autowired
    private GridService gridService;

    @Autowired
    private SlideService slideService;

    public List<Slide> doAnalysis(List<Slide> slides, Long userId) {
        List<List<Grid>> result = new ArrayList<>();

        slides.forEach(slide -> {
            if (slide != null && slide.getId() != null && slide.getStatus() == Status.NONE) {
                result.add(createSampleGridsForSlide(slide, userId));
            }
        });

        return slides;
    }

    private List<Grid> createSampleGridsForSlide(Slide slide, Long userId) {
        Grid[] gridData = {
                new Grid(1l, 0.9, 9.99, 81.8, 27.3, 99.5, 134.9, userId, slide.getId()),
                new Grid(2l, 2.9, 19.99, 82.8, 37.3, 89.5, 234.9, userId, slide.getId()),
                new Grid(3l, 3.9, 29.99, 83.8, 47.3, 79.5, 334.9, userId, slide.getId()),
                new Grid(4l, 4.9, 39.99, 84.8, 47.3, 69.5, 434.9, userId, slide.getId()),
                new Grid(5l, 5.9, 49.99, 85.8, 57.3, 59.5, 534.9, userId, slide.getId()),
                new Grid(6l, 6.9, 59.99, 86.8, 67.3, 49.5, 534.9, userId, slide.getId()),
                new Grid(7l, 7.9, 69.99, 87.8, 77.3, 39.5, 634.9, userId, slide.getId()),
                new Grid(8l, 8.9, 79.99, 88.8, 87.3, 39.5, 734.9, userId, slide.getId())
        };
        try {
            List<Grid> grids = new ArrayList<>(Arrays.asList(gridData));
            List<Grid> _grids = gridService.insertAll(grids);

            //Set slideStatus as COMPLETED for not processing again
            //Set values of AI result (decision, score)
            slide.setStatus(Status.COMPLETED);
            slide.setDecision(1);
            slide.setScore(86.0);
            slide.setIsProcessed(Boolean.TRUE);
            slide.setUserId(userId);
            Slide _slide = slideService.update(slide);
            return _grids;
        } catch (GridNotFoundException e) {
            throw new GridNotFoundException("Grid not found.");
        } catch (SlideNotFoundException e) {
            throw new SlideNotFoundException("Slide not found.");
        } catch (DbException dbException) {
            throw new DbException("Database is failed.");
        } catch (Exception ex) {
            throw new InvalidRequestException("Invalid request.");
        }
    }
}
