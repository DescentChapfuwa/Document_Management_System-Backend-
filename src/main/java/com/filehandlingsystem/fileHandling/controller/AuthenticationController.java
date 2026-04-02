package com.filehandlingsystem.fileHandling.controller;

import com.filehandlingsystem.fileHandling.dto.LoginRequest;
import com.filehandlingsystem.fileHandling.dto.LoginResponse;
import com.filehandlingsystem.fileHandling.entities.User;
import com.filehandlingsystem.fileHandling.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final UserService userService;

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

    @GetMapping("/me")
    public ResponseEntity<Object> getCurrentUser(Authentication authentication){
        return ResponseEntity.ok(authentication.getPrincipal());
    }

}
