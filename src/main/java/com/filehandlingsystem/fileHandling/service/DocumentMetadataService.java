package com.filehandlingsystem.fileHandling.service;

import com.filehandlingsystem.fileHandling.entities.DocumentMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentMetadataService {

    DocumentMetadata uploadDocument(MultipartFile file,String userName);

    void delete(String id,String userName);

    List<DocumentMetadata> listAllDocuments();

    DocumentMetadata findDocumentById(String id,String userName);
}
