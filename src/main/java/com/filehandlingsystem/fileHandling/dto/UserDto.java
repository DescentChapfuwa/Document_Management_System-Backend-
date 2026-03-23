package com.filehandlingsystem.fileHandling.dto;

public class UserDto {
    private String firstName;

    private String lastName;

    private String department;

    private String password;

    private String role;

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

    public UserDto(String firstName, String lastName, String department, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.password = password;
        this.role = role;
    }
}
