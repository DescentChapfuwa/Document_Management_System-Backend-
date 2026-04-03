package com.filehandlingsystem.fileHandling.service;

import com.filehandlingsystem.fileHandling.dto.LoginRequest;
import com.filehandlingsystem.fileHandling.dto.LoginResponse;
import com.filehandlingsystem.fileHandling.entities.RefreshToken;
import com.filehandlingsystem.fileHandling.entities.User;

import java.util.List;

public interface UserService {

    User register(User user);

    void delete(Long id);

    LoginResponse login(LoginRequest request);

    String generateRefreshToken(String refreshToken);

    void update(User user,Long id);

    List<User> allUsers();

    User findUserById(Long id);
}
