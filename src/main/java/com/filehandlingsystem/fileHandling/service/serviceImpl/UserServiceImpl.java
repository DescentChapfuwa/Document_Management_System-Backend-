package com.filehandlingsystem.fileHandling.service.serviceImpl;

import com.filehandlingsystem.fileHandling.dto.LoginRequest;
import com.filehandlingsystem.fileHandling.dto.LoginResponse;
import com.filehandlingsystem.fileHandling.entities.RefreshToken;
import com.filehandlingsystem.fileHandling.entities.User;
import com.filehandlingsystem.fileHandling.exception.BadRequestException;
import com.filehandlingsystem.fileHandling.exception.TokenExpiredException;
import com.filehandlingsystem.fileHandling.exception.TokenNotFound;
import com.filehandlingsystem.fileHandling.exception.UserNotFound;
import com.filehandlingsystem.fileHandling.repository.RefreshTokenRepository;
import com.filehandlingsystem.fileHandling.repository.UserRepository;
import com.filehandlingsystem.fileHandling.service.JwtService;
import com.filehandlingsystem.fileHandling.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public User register(User user) {

        Boolean userNameExists = userRepository.existsByUserName(user.getUserName());

        if(userNameExists){
            throw new BadRequestException("Username is already taken");
        }

        User newUser = new User();
        newUser.setDepartment(user.getDepartment());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setRole(user.getRole());
        newUser.setUserName(user.getUserName());
        newUser.setDepartment(user.getDepartment());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setDocuments(user.getDocuments());

        userRepository.save(newUser);

        return newUser;
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByUserName(request.getUserName());

        String token = jwtService.generateToken(user);

        RefreshToken refreshToken = jwtService.createRefreshToken(user.getUserName());

        String expiresAt = "15 min";

        LoginResponse response = new LoginResponse(token,refreshToken.getToken(),new Date(),expiresAt);
        return response;
    }

    @Override
    public String generateRefreshToken(String token) {
        Boolean tokenInDB = refreshTokenRepository.existsByToken(token);
        if(!tokenInDB){
            throw new TokenNotFound("Invalid refresh Token");
        }
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token);

        User user = userRepository.findByUserName(refreshToken.getUserName());

        if(refreshToken.getExpiryDate().before(new Date())){
            throw new TokenExpiredException("Token has expired");
        }
        return jwtService.generateToken(user);
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
        if(userInDb.isEmpty()){
            throw new UserNotFound("User with id: "+id+" was  not found");
        }

        User existingUser = userInDb.get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setDepartment(user.getDepartment());
        existingUser.setPassword(user.getPassword());
        existingUser.setRole(user.getRole());
        existingUser.setDocuments(user.getDocuments());

        userRepository.save(existingUser);
    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        Optional<User> userInDb = userRepository.findById(id);
        if(userInDb.isEmpty()){
            throw new UserNotFound("User with "+id+"was  not found");
        }
        return userInDb.get();
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Boolean userExists = userRepository.existsByUserName(userName);
        if(!userExists){
            throw new UserNotFound("User with username: "+userName+" was not found");
        }

        User user = userRepository.findByUserName(userName);
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUserName())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
