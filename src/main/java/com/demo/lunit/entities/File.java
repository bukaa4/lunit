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
@Table(name = "files")
public class File {
    @Id
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

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

    @Column(name="slide_id")
    private Long slideId;
}