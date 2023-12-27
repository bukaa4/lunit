package com.demo.lunit.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "grids")
public class Grid {
    @Id
    @Column(name="id")
    private Long id;

    @Column(name="intratumoral_min")
    private Double intratumoralMin;

    @Column(name="intratumoral_avg")
    private Double intratumoralAvg;

    @Column(name="intratumoral_max")
    private Double intratumoralMax;

    @Column(name="stromal_min")
    private Double stromalMin;

    @Column(name="stromal_avg")
    private Double stromalAvg;

    @Column(name="stromal_max")
    private Double stromalMax;

    @Column(name="slide_id")
    private Long slideId;

    @Column(name="user_id")
    private Long userId;

    protected Grid(){

    }
    public Grid(Long id, Double intratumoralMin, Double intratumoralAvg, Double intratumoralMax, Double stromalMin, Double stromalAvg, Double stromalMax, Long userId, Long slideId) {
        this.id = id;
        this.intratumoralMin = intratumoralMin;
        this.intratumoralAvg = intratumoralAvg;
        this.intratumoralMax = intratumoralMax;
        this.stromalMin = stromalMin;
        this.stromalAvg = stromalAvg;
        this.stromalMax = stromalMax;
        this.userId = userId;
        this.slideId = slideId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getIntratumoralMin() {
        return intratumoralMin;
    }

    public void setIntratumoralMin(Double intratumoralMin) {
        this.intratumoralMin = intratumoralMin;
    }

    public Double getIntratumoralAvg() {
        return intratumoralAvg;
    }

    public void setIntratumoralAvg(Double intratumoralAvg) {
        this.intratumoralAvg = intratumoralAvg;
    }

    public Double getIntratumoralMax() {
        return intratumoralMax;
    }

    public void setIntratumoralMax(Double intratumoralMax) {
        this.intratumoralMax = intratumoralMax;
    }

    public Double getStromalMin() {
        return stromalMin;
    }

    public void setStromalMin(Double stromalMin) {
        this.stromalMin = stromalMin;
    }

    public Double getStromalAvg() {
        return stromalAvg;
    }

    public void setStromalAvg(Double stromalAvg) {
        this.stromalAvg = stromalAvg;
    }

    public Double getStromalMax() {
        return stromalMax;
    }

    public void setStromalMax(Double stromalMax) {
        this.stromalMax = stromalMax;
    }

    public Long getSlideId() {
        return slideId;
    }

    public void setSlideId(Long slideId) {
        this.slideId = slideId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
