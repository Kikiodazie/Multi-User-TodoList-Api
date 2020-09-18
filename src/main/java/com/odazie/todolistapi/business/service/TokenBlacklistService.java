package com.odazie.todolistapi.business.service;

import com.auth0.jwt.JWT;
import com.odazie.todolistapi.data.entity.JwtTokenBlacklist;
import com.odazie.todolistapi.data.repository.JwtTokenBlacklistRepository;
import org.springframework.stereotype.Service;

@Service
public class TokenBlacklistService {

    private final JwtTokenBlacklistRepository blacklistRepository;


    public TokenBlacklistService(JwtTokenBlacklistRepository blacklistRepository) {
        this.blacklistRepository = blacklistRepository;
    }


    public void addTokenToBlacklist(String token, JwtTokenBlacklist tokenBlacklist){
        long expirationTime = JWT.decode(token).getExpiresAt().getTime();
        tokenBlacklist.setToken(token);
        tokenBlacklist.setExpiryAt(expirationTime);
        getBlacklistRepository().save(tokenBlacklist);
    }

    public JwtTokenBlacklistRepository getBlacklistRepository() {
        return blacklistRepository;
    }
}
