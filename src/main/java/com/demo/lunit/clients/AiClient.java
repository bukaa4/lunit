package com.demo.lunit.clients;

import com.demo.lunit.entities.Grid;
import com.demo.lunit.entities.Slide;
import com.demo.lunit.services.GridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Controller
public class AiClient {

    @Autowired
    private GridService gridService;

    public List<Grid> doAnalysis(Slide slide) {
        List<Grid> grids = null;

        if (slide != null && slide.getId() != null && slide.getUserId() != null) {
            grids = createSampleData(slide.getId(), slide.getUserId());
        }

        return grids;
    }

    private List<Grid> createSampleData(Long slideId, Long userId) {
        Grid[] gridData = {
                new Grid(1l, 0.9, 9.99, 81.8, 27.3, 99.5, 134.9, userId, slideId),
                new Grid(2l, 2.9, 19.99, 82.8, 37.3, 89.5, 234.9, userId, slideId),
                new Grid(3l, 3.9, 29.99, 83.8, 47.3, 79.5, 334.9, userId, slideId),
                new Grid(4l, 4.9, 39.99, 84.8, 47.3, 69.5, 434.9, userId, slideId),
                new Grid(5l, 5.9, 49.99, 85.8, 57.3, 59.5, 534.9, userId, slideId),
                new Grid(6l, 6.9, 59.99, 86.8, 67.3, 49.5, 534.9, userId, slideId),
                new Grid(7l, 7.9, 69.99, 87.8, 77.3, 39.5, 634.9, userId, slideId),
                new Grid(8l, 8.9, 79.99, 88.8, 87.3, 39.5, 734.9, userId, slideId)
        };
        List<Grid> grids = new ArrayList<>(Arrays.asList(gridData));
        List<Grid> _grids = gridService.insertAll(grids);
        //grids.forEach(System.out::println);
        return _grids;
    }
}
