package com.filehandlingsystem.fileHandling.repository;

import com.filehandlingsystem.fileHandling.entities.DocumentMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentMetadataRepository extends JpaRepository<DocumentMetadata,Long> {
    List<DocumentMetadata> getDocumentByOwnerId(String id);

    Optional<DocumentMetadata> findById(String id);
}
