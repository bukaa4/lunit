package com.demo.lunit.services;

import com.demo.lunit.entities.File;

import java.util.List;
import java.util.Optional;

public interface FileService {
    List<File> findAllFile();
    Optional<File> findById(Long id);
    File insert(File File);
    List<File> insertAll(List<File> files);
    void deleteById(Long id);
    File update(File file);
}
