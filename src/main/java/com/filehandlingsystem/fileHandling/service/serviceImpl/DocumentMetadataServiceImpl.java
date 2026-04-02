package com.filehandlingsystem.fileHandling.service.serviceImpl;

import com.filehandlingsystem.fileHandling.config.FileStorageProperties;
import com.filehandlingsystem.fileHandling.entities.DocumentMetadata;
import com.filehandlingsystem.fileHandling.entities.User;
import com.filehandlingsystem.fileHandling.exception.*;
import com.filehandlingsystem.fileHandling.repository.DocumentMetadataRepository;
import com.filehandlingsystem.fileHandling.repository.UserRepository;
import com.filehandlingsystem.fileHandling.service.DocumentMetadataService;
import com.filehandlingsystem.fileHandling.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class DocumentMetadataServiceImpl implements DocumentMetadataService {

    FileStorageService fileStorageService;

    private final Path rootLocation;

    DocumentMetadataRepository documentMetadataRepository;

    UserRepository userRepository;

    public DocumentMetadataServiceImpl(FileStorageService fileStorageService, FileStorageProperties fileStorageProperties, DocumentMetadataRepository documentMetadataRepository, UserRepository userRepository) {

        if (fileStorageProperties.getLocation().trim().length() ==0 ){
            throw new StorageException("File upload location can not be empty");
        }

        this.rootLocation = Paths.get(fileStorageProperties.getLocation());
        this.fileStorageService = fileStorageService;
        this.documentMetadataRepository = documentMetadataRepository;
        this.userRepository = userRepository;
    }



    @Override
    @Transactional
    public DocumentMetadata uploadDocument(MultipartFile file, String userName) {

        if (file.isEmpty()) {
            throw new StorageException("File cannot be empty");
        }

        try{

            Boolean exists  = userRepository.existsByUserName(userName);

            if(!exists){
                throw new UserNotFound("User with username: "+userName+" was not found");
            }

            User owner = userRepository.findByUserName(userName);


            String id = UUID.randomUUID().toString();

            DocumentMetadata metadata = new DocumentMetadata();
            metadata.setId(id);
            metadata.setFileName(file.getOriginalFilename());
            metadata.setOwnerId(owner);
            metadata.setCreatedAt(Instant.now());

            documentMetadataRepository.save(metadata);

            fileStorageService.store(file,id);

            return metadata;
        } catch (Exception e) {
            throw new StorageException("Failed to store the file",e);
        }
    }

    @Override
    @Transactional
    public void delete(String id,String userName) {
        DocumentMetadata doc = documentMetadataRepository
                .findById(id)
                .orElseThrow(()->new
                        DocumentNotFound("Document with id: "+id+" was not found"));

        if(doc.getOwnerId().getUserName().equals(userName)){
            throw new AccessDeniedException("Access has been denied to "+userName);
        }
         try{

             String storedFilename = doc.getId()+"_"+doc.getFileName();
             Path filePath = rootLocation.resolve(storedFilename);
             Files.deleteIfExists(filePath);
             documentMetadataRepository.delete(doc);
         } catch (IOException e) {
             throw new StorageException("Failed to delete document");
         }
    }



    @Override
    public List<DocumentMetadata> listAllDocuments() {
        return documentMetadataRepository.findAll();
    }

    @Override
    public DocumentMetadata findDocumentById(String id,String userName) {
        DocumentMetadata docInDB = documentMetadataRepository
                .findById(id).
                orElseThrow(()-> new DocumentNotFound("Document with id: "+id+" was not found"));

        if(!docInDB.getOwnerId().getUserName().equals(userName)){
            throw new AccessDeniedException("Access has been denied");
        }
        return docInDB;
    }
}
