package com.filehandlingsystem.fileHandling.service;

import com.filehandlingsystem.fileHandling.entities.DocumentMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentMetadataService {

    DocumentMetadata uploadDocument(MultipartFile file,Long ownerId);

    void delete(Long id);

    void update(DocumentMetadata metadata,Long id);

    List<DocumentMetadata> listAllDocuments();

    DocumentMetadata findDocumentByOwnerId(Long id);
}
