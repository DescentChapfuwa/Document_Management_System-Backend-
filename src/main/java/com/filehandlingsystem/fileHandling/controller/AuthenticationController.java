package com.filehandlingsystem.fileHandling.controller;

import com.filehandlingsystem.fileHandling.dto.LoginRequest;
import com.filehandlingsystem.fileHandling.dto.LoginResponse;
import com.filehandlingsystem.fileHandling.entities.User;
import com.filehandlingsystem.fileHandling.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        userService.register(user);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        LoginResponse response =  userService.login(request);
        return ResponseEntity.ok().body(response);
    }

}
