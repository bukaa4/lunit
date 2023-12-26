package com.demo.lunit.services;

import com.demo.lunit.entities.File;
import com.demo.lunit.respositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;

    @Override
    public List<File> findAllFile() { //public Iterable<File> findAllFiles() {
        return this.fileRepository.findAll();
    }

    @Override
    public Optional<File> findById(Long id) {
        return this.fileRepository.findById(id);
    }

    @Override
    public File insert(File file) {
        return this.fileRepository.save(file);
    }

    @Override
    public List<File> insertAll(List<File> files) {
        return this.fileRepository.saveAll(files);
    }

    @Override
    public void deleteById(Long id) {
        this.fileRepository.deleteById(id);
    }

    @Override
    public File update(File file) {
        return this.fileRepository.save(file);
    }
}
