package com.filehandlingsystem.fileHandling.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String department;

    private String password;

    private String role;

    @OneToMany(mappedBy = "ownerId",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<DocumentMetadata> documents;

}
