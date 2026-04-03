package com.filehandlingsystem.fileHandling.dto;

import com.filehandlingsystem.fileHandling.entities.RefreshToken;

import java.util.Date;

public class LoginResponse {

    private String token;

    private String refreshToken;

    private Date issuedAt;

    private String expiresAt;

    public LoginResponse(String token,String refreshToken, Date issuedAt, String expiresAt) {
        this.token = token;
        this.issuedAt = issuedAt;
        this.refreshToken = refreshToken;
        this.expiresAt = expiresAt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }
}
