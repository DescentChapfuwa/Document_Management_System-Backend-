package com.filehandlingsystem.fileHandling.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import java.time.Instant;
import java.util.UUID;


@Entity
@Data
public class DocumentMetadata {

    @Id
    private  String id;

    private  String fileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private  User ownerId;

    private  Instant createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public DocumentMetadata(String id, String fileName, User ownerId, Instant createdAt) {
        this.id = id;
        this.fileName = fileName;
        this.ownerId = ownerId;
        this.createdAt = createdAt;
    }

    public DocumentMetadata() {
    }
}
