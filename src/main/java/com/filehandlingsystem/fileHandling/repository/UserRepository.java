package com.filehandlingsystem.fileHandling.repository;

import com.filehandlingsystem.fileHandling.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
