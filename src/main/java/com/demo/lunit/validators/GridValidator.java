package com.demo.lunit.validators;

import com.demo.lunit.entities.Grid;
import com.demo.lunit.exceptions.InvalidRequestException;
import org.springframework.stereotype.Component;

@Component
public class GridValidator {
    public void validate(Grid grid) {
        if (grid == null) {
            throw new InvalidRequestException("grid is null.");
        }
        if (grid.getIntratumoralMin() == null) {
            throw new InvalidRequestException("getIntratumoralMin field is invalid.");
        }
        if (grid.getIntratumoralAvg() == null) {
            throw new InvalidRequestException("getIntratumoralAvg field is invalid.");
        }
        if (grid.getIntratumoralMax() == null) {
            throw new InvalidRequestException("getIntratumoralMax field is invalid.");
        }
        if (grid.getStromalMin() == null) {
            throw new InvalidRequestException("getStromalMin field is invalid.");
        }
        if (grid.getStromalAvg() == null) {
            throw new InvalidRequestException("getStromalAvg field is invalid.");
        }
        if (grid.getStromalMax() == null) {
            throw new InvalidRequestException("getStromalMax field is invalid.");
        }
    }
}
