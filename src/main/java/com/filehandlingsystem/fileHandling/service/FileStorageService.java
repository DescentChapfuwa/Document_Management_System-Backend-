package com.filehandlingsystem.fileHandling.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.UUID;
import java.util.stream.Stream;

public interface FileStorageService {

    void init();

    Boolean store(MultipartFile file, String id);

    Stream<Path> loadAll();

    Path load(String fileName);

    Resource loadAsResource(String fileName);


    void deleteAll();
}
