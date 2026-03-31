package com.filehandlingsystem.fileHandling.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToMany(mappedBy = "ownerId",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<DocumentMetadata> documents;

}
