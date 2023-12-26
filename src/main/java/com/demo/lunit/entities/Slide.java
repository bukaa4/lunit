package com.demo.lunit.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "slides")
public class Slide {
    @Id
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="processed")
    private Boolean isProcessed;

    @Column(name="status")
    private Status status;

    @Column(name="decision")
    private Integer decision;

    @Column(name="score")
    private Float score;
}
