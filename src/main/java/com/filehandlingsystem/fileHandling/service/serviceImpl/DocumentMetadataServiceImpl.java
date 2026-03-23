package com.filehandlingsystem.fileHandling.service.serviceImpl;

import com.filehandlingsystem.fileHandling.entities.DocumentMetadata;
import com.filehandlingsystem.fileHandling.repository.DocumentMetadataRepository;
import com.filehandlingsystem.fileHandling.service.DocumentMetadataService;
import com.filehandlingsystem.fileHandling.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.List;


@Service
public class DocumentMetadataServiceImpl implements DocumentMetadataService {

    FileStorageService fileStorageService;

    DocumentMetadataRepository documentMetadataRepository;

    public DocumentMetadataServiceImpl(FileStorageService fileStorageService, DocumentMetadataRepository documentMetadataRepository) {
        this.fileStorageService = fileStorageService;
        this.documentMetadataRepository = documentMetadataRepository;
    }



    @Override
    @Transactional
    public String save(MultipartFile file,Long ownerId) {

      return "";
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(DocumentMetadata metadata, Long id) {

    }

    @Override
    public List<DocumentMetadata> listAllDocuments() {
        return null;
    }

    @Override
    public DocumentMetadata findDocumentByOwnerId(Long id) {
        return null;
    }
}
