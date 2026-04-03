package com.filehandlingsystem.fileHandling.service;

import com.filehandlingsystem.fileHandling.entities.RefreshToken;
import com.filehandlingsystem.fileHandling.entities.User;
import com.filehandlingsystem.fileHandling.repository.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String SECRET;

    SecretKey key;

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(SECRET.getBytes());
    }


    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getUserName())
                .claim("role", user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System
                        .currentTimeMillis() + 86400000))
                .signWith(key)
                .compact();
    }

    public RefreshToken createRefreshToken(String userName){
        RefreshToken token = new RefreshToken();
        token.setUserName(userName);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(new Date(System.currentTimeMillis()+604800000));

        return refreshTokenRepository.save(token);
    }

    public  String extractUsername(String token){
        return  extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction){
        return claimsTFunction.apply(Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload());
    }

    public Boolean isTokenValid(String token,String userName){
        String extractedUsername = extractUsername(token);
        return extractedUsername.equals(userName);
    }

}
