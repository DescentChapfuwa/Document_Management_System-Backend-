package com.filehandlingsystem.fileHandling.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.time.Instant;
import java.util.UUID;


@Entity
public class DocumentMetadata {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    private  String fileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private  User ownerId;

    private  Instant createdAt;

    public DocumentMetadata(Long id, String fileName, User ownerId, Instant createdAt) {


        if(fileName.isBlank()){
            throw new IllegalArgumentException("Document filename cannot be empty");
        }

        if(ownerId == null){
            throw new IllegalArgumentException("Owner's id cannot be null");
        }

        this.id = id;
        this.fileName = fileName;
        this.ownerId = ownerId;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public User getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(User ownerId) {
        this.ownerId = ownerId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
