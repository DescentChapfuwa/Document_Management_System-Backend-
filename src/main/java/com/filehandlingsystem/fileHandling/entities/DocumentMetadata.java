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


}
