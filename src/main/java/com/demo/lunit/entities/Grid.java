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
@Table(name = "grids")
public class Grid {
    @Id
    @Column(name="id")
    private Long id;

    //fileID FK
    @Column(name="file_id")
    private Long fileId;

    @Column(name="intratumoral_min")
    private Float intratumoralMin;

    @Column(name="intratumoral_avg")
    private Float intratumoralAvg;

    @Column(name="intratumoral_max")
    private Float intratumoralMax;

    @Column(name="stromal_min")
    private Float stromalMin;

    @Column(name="stromal_avg")
    private Float stromalAvg;

    @Column(name="stromal_max")
    private Float stromalMax;
}
