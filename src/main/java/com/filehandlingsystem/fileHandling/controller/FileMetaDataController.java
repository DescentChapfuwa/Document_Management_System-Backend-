package com.filehandlingsystem.fileHandling.controller;

import com.filehandlingsystem.fileHandling.entities.DocumentMetadata;
import com.filehandlingsystem.fileHandling.service.DocumentMetadataService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.RequestEntity.delete;

@RestController
@RequestMapping("document")
@EnableMethodSecurity
public class FileMetaDataController {

    private DocumentMetadataService documentMetadataService;

    public FileMetaDataController(DocumentMetadataService documentMetadataService) {
        this.documentMetadataService = documentMetadataService;
    }


    @PostMapping("/upload/{ownerId}")
    public ResponseEntity<DocumentMetadata> handleDocumentUpload(
            @RequestParam("file") MultipartFile file,
            Authentication authentication) {
        DocumentMetadata documentMetadata = documentMetadataService.uploadDocument(file, authentication.getName());
        return ResponseEntity.ok(documentMetadata);
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<DocumentMetadata>> getAll(){
        return ResponseEntity.ok(documentMetadataService.listAllDocuments());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<DocumentMetadata> findDocumentById(@PathVariable String id, Authentication authentication){
        DocumentMetadata docFound = documentMetadataService.findDocumentById(id, authentication.getName());
        return ResponseEntity.ok(docFound);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteDocument(@PathVariable String id,Authentication authentication){
        documentMetadataService.delete(id, authentication.getName());
        return ResponseEntity.noContent().build();
    }
}
