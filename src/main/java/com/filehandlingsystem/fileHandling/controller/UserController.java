package com.filehandlingsystem.fileHandling.controller;

import com.filehandlingsystem.fileHandling.entities.User;
import com.filehandlingsystem.fileHandling.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id){
        userService.update(user,id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id){
       User userFound =  userService.findUserById(id);
        return ResponseEntity.ok().body(userFound);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUsers = userService.allUsers();
        return ResponseEntity.ok().body(allUsers);
    }

}
