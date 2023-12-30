package com.demo.lunit.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "slides")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Slide {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
