package com.demo.lunit.respositories;

import com.demo.lunit.entities.Grid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GridRepository extends JpaRepository<Grid, Long> {
}
