package com.filehandlingsystem.fileHandling.repository;

import com.filehandlingsystem.fileHandling.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    RefreshToken findByToken(String token);

    Boolean existsByToken(String token);
}
