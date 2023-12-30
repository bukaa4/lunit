package com.demo.lunit.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "grids")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Grid {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name="user_id")
    private Long userId;

    @Column(name="slide_id")
    private Long slideId;
}
