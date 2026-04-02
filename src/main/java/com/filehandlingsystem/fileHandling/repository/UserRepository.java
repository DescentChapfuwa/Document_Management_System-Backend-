package com.filehandlingsystem.fileHandling.repository;

import com.filehandlingsystem.fileHandling.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String userName);

    Boolean existsByUserName(String userName);
}
