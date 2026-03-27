package com.filehandlingsystem.fileHandling.service.serviceImpl;

import com.filehandlingsystem.fileHandling.entities.DocumentMetadata;
import com.filehandlingsystem.fileHandling.entities.User;
import com.filehandlingsystem.fileHandling.exception.DocumentNotFound;
import com.filehandlingsystem.fileHandling.exception.StorageException;
import com.filehandlingsystem.fileHandling.exception.UserNotFound;
import com.filehandlingsystem.fileHandling.repository.DocumentMetadataRepository;
import com.filehandlingsystem.fileHandling.repository.UserRepository;
import com.filehandlingsystem.fileHandling.service.DocumentMetadataService;
import com.filehandlingsystem.fileHandling.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class DocumentMetadataServiceImpl implements DocumentMetadataService {

    FileStorageService fileStorageService;

    DocumentMetadataRepository documentMetadataRepository;

    UserRepository userRepository;

    public DocumentMetadataServiceImpl(FileStorageService fileStorageService, DocumentMetadataRepository documentMetadataRepository,UserRepository userRepository) {
        this.fileStorageService = fileStorageService;
        this.documentMetadataRepository = documentMetadataRepository;
        this.userRepository = userRepository;
    }



    @Override
    @Transactional
    public DocumentMetadata uploadDocument(MultipartFile file, Long ownerId) {

        if (file.isEmpty()) {
            throw new StorageException("File cannot be empty");
        }

        try{

            Optional<User> owner = userRepository.findById(ownerId);
            if(!owner.isPresent()){
                throw new UserNotFound("User with "+ownerId+"was  not found");
            }

            String id = UUID.randomUUID().toString();

            DocumentMetadata metadata = new DocumentMetadata();
            metadata.setId(id);
            metadata.setFileName(file.getOriginalFilename());
            metadata.setOwnerId(owner.get());
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
    public void delete(Long id) {


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
