package com.demo.lunit.respositories;

import com.demo.lunit.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}