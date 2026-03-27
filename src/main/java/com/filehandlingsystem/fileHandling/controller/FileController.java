package com.filehandlingsystem.fileHandling.controller;

import com.filehandlingsystem.fileHandling.dto.FileResponseDto;
import com.filehandlingsystem.fileHandling.exception.StorageException;
import com.filehandlingsystem.fileHandling.exception.StorageFileNotFoundException;
import com.filehandlingsystem.fileHandling.service.FileStorageService;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.File;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("files")
public class FileController {

    private final FileStorageService storageService;

    FileController(FileStorageService storageService){
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<FileResponseDto> handleFileUpload(@RequestParam("file") MultipartFile file,String id){
        storageService.store(file,id);
        FileResponseDto fileResponseDto = new FileResponseDto();
        fileResponseDto.setFileName(file.getName());
        fileResponseDto.setContentType(file.getContentType());
        fileResponseDto.setSize(file.getSize());
        fileResponseDto.setCreatedAt(Instant.now());

        return ResponseEntity.status(HttpStatus.OK).body(fileResponseDto);
    }

    @GetMapping("/")
    public ResponseEntity<List<String>> listUploadedFiles(){
        List<String> allFiles = storageService
                .loadAll()
                .map(path -> MvcUriComponentsBuilder
                        .fromMethodName(FileController.class,"download",path
                                .getFileName()
                                .toString())
                        .build()
                        .toUri()
                        .toString())
                .collect(Collectors.toList());
        if(allFiles.isEmpty()){
            throw new StorageFileNotFoundException("No files found");
        }
        return ResponseEntity.ok().body(allFiles);
    }

    @GetMapping("/download/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<Resource> download(@PathVariable String fileName){
        Resource file = storageService.loadAsResource(fileName);
        if(file == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+ file.getFilename() + "\"").body(file);
    }

    @DeleteMapping("/delete/{fileName:.+}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName){
        File file = storageService.load(fileName).toFile();
        if(!file.delete()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File "+fileName+" was not found");
        }
        return ResponseEntity.ok().body("File "+fileName+" has been deleted");
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllFiles(){
        storageService.deleteAll();
        return ResponseEntity.ok().body("All the files have been deleted");
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageNotFoundException(StorageFileNotFoundException exc){
        return ResponseEntity.notFound().build();

    }
}
