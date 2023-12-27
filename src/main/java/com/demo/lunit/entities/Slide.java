package com.demo.lunit.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "slides")
public class Slide {
    @Id
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="processed")
    private Boolean isProcessed;

    @Column(name="status")
    private Status status;

    @Column(name="decision")
    private Integer decision;

    @Column(name="score")
    private Double score;

    @Column(name="description")
    private String description;

    @Column(name="format")
    private String format;

    @Column(name="license")
    private String license;

    @Column(name="size")
    private Long size;

    @Column(name="sha256")
    private String sha256;

    @Column(name = "user_id")
    private Long userId;

   // private List<Grid> grids;

    protected Slide(){}
    public Slide(Long id, String name, Boolean isProcessed, Status status, Integer decision, Double score, String description, String format, String license, Long size, String sha256, Long userId) {
        this.id = id;
        this.name = name;
        this.isProcessed = isProcessed;
        this.status = status;
        this.decision = decision;
        this.score = score;
        this.description = description;
        this.format = format;
        this.license = license;
        this.size = size;
        this.sha256 = sha256;
        this.userId = userId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getProcessed() {
        return isProcessed;
    }

    public void setProcessed(Boolean processed) {
        isProcessed = processed;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getDecision() {
        return decision;
    }

    public void setDecision(Integer decision) {
        this.decision = decision;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getSha256() {
        return sha256;
    }

    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
