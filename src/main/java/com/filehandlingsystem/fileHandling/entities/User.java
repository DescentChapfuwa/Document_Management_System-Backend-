package com.filehandlingsystem.fileHandling.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String password;


    private String role;

    @OneToMany(mappedBy = "ownerId",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<DocumentMetadata> documents;

    public User(Long id, String password, String role, List<DocumentMetadata> documents) {

        if(password.isEmpty()||password.isBlank()) throw new IllegalArgumentException("Password cannot be blank or empty");

        if(role.isBlank()||role.isEmpty()) throw new IllegalArgumentException("Role cannot be empty or blank");

        this.id = id;
        this.password = password;
        this.role = role;
        this.documents = documents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<DocumentMetadata> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentMetadata> documents) {
        this.documents = documents;
    }
}
