package com.filehandlingsystem.fileHandling.repository;

import com.filehandlingsystem.fileHandling.entities.DocumentMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentMetadataRepository extends JpaRepository<DocumentMetadata,Long> {
    List<DocumentMetadata> getDocumentByOwnerId(Long id);

}
