package com.filehandlingsystem.fileHandling.service.serviceImpl;

import com.filehandlingsystem.fileHandling.entities.User;
import com.filehandlingsystem.fileHandling.exception.UserNotFound;
import com.filehandlingsystem.fileHandling.repository.UserRepository;
import com.filehandlingsystem.fileHandling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {

        User newUser = new User();
        newUser.setDepartment(user.getDepartment());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setRole(user.getRole());
        newUser.setDepartment(user.getDepartment());
        newUser.setPassword(user.getPassword());
        newUser.setDocuments(user.getDocuments());

        userRepository.save(newUser);

        return newUser;
    }

    @Override
    public void delete(Long id) {
        Optional<User> userInDb = userRepository.findById(id);
        if(!userInDb.isPresent()){
            throw new UserNotFound("User with "+id+"was  not found");
        }
        userRepository.delete(userInDb.get());
    }

    @Override
    public void update(User user, Long id) {
        Optional<User> userInDb = userRepository.findById(id);
        if(!userInDb.isPresent()){
            throw new UserNotFound("User with "+id+"was  not found");
        }

        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setDepartment(user.getDepartment());
        newUser.setPassword(user.getPassword());
        newUser.setRole(user.getRole());
        newUser.setDocuments(user.getDocuments());

    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        Optional<User> userInDb = userRepository.findById(id);
        if(!userInDb.isPresent()){
            throw new UserNotFound("User with "+id+"was  not found");
        }
        return userInDb.get();
    }
}
