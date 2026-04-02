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

    @Column(unique = true,nullable = false)
    private String userName;

    private String department;

    private String password;

    private String role;


    @JsonIgnore
    @OneToMany(mappedBy = "ownerId",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<DocumentMetadata> documents;


    public User(Long id, String firstName, String lastName, String department,String userName, String password, String role, List<DocumentMetadata> documents) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.password = password;
        this.userName = userName;
        this.role = role;
        this.documents = documents;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
