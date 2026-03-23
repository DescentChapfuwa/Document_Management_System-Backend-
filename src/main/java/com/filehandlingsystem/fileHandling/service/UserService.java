package com.filehandlingsystem.fileHandling.service;

import com.filehandlingsystem.fileHandling.entities.User;

import java.util.List;

public interface UserService {

    User save(User user);

    void delete(Long id);

    void update(User user,Long id);

    List<User> allUsers();

    User findUserById(Long id);
}
