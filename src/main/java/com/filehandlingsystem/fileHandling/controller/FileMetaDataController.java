package com.filehandlingsystem.fileHandling.controller;

import com.filehandlingsystem.fileHandling.entities.DocumentMetadata;
import com.filehandlingsystem.fileHandling.service.DocumentMetadataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.RequestEntity.delete;

@RestController
@RequestMapping("document")
public class FileMetaDataController {

    private DocumentMetadataService documentMetadataService;

    public FileMetaDataController(DocumentMetadataService documentMetadataService) {
        this.documentMetadataService = documentMetadataService;
    }


    @PostMapping("/upload/{ownerId}")
    public ResponseEntity<DocumentMetadata> handleDocumentUpload(
            @RequestParam("file") MultipartFile file,
            @PathVariable Long ownerId) {
        DocumentMetadata documentMetadata = documentMetadataService.uploadDocument(file, ownerId);
        return ResponseEntity.ok(documentMetadata);
    }

    @GetMapping("/")
    public ResponseEntity<List<DocumentMetadata>> getAll(){
        return ResponseEntity.ok(documentMetadataService.listAllDocuments());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<DocumentMetadata> findDocumentById(@PathVariable String id){
        DocumentMetadata docFound = documentMetadataService.findDocumentById(id);
        return ResponseEntity.ok(docFound);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDocument(@PathVariable String id){
        documentMetadataService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
