package com.odazie.todolistapi.business.service;

import com.auth0.jwt.JWT;
import com.odazie.todolistapi.data.entity.JwtTokenBlacklist;
import com.odazie.todolistapi.data.repository.JwtTokenBlacklistRepository;
import com.odazie.todolistapi.security.JWTAuthorizationFilter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

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

    public JwtTokenBlacklist blacklistCheck(HttpServletRequest request){
       return getBlacklistRepository().findByToken(JWTAuthorizationFilter.getToken(request));
    }
    public JwtTokenBlacklistRepository getBlacklistRepository() {
        return blacklistRepository;
    }
}
