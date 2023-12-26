package com.demo.lunit.controllers;

import com.demo.lunit.entities.File;
import com.demo.lunit.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/files")
    public Iterable<File> findAllFiles() {
        return fileService.findAllFile();
    }

    @PostMapping("/file")
    public File addOneFile(@RequestBody File file) {
        return fileService.insert(file);
    }

    @PostMapping("/files")
    public List<File> addBulkFile(@RequestBody List<File> files) {
        return fileService.insertAll(files);
    }
}
