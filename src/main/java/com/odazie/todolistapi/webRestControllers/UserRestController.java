package com.odazie.todolistapi.webRestControllers;

import com.auth0.jwt.JWT;
import com.odazie.todolistapi.business.service.TokenBlacklistService;
import com.odazie.todolistapi.business.service.UserService;
import com.odazie.todolistapi.data.entity.JwtTokenBlacklist;
import com.odazie.todolistapi.data.entity.User;
import com.odazie.todolistapi.security.JWTAuthorizationFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UserRestController {

    private final UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenBlacklistService tokenBlacklistService;


    public UserRestController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder, TokenBlacklistService tokenBlacklistService) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> userSignUp(@RequestBody User user, UriComponentsBuilder builder){
        if (userService.getUserRepository().findByEmail(user.getEmail()) != null){
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.getUserRepository().save(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/users/{userId}").buildAndExpand(user.getUserId()).toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

    }

    @GetMapping("/logt")
    public ResponseEntity<JwtTokenBlacklist> logoutUser(JwtTokenBlacklist tokenBlacklist, HttpServletRequest request, Authentication authentication) throws ServletException {
        String token = JWTAuthorizationFilter.getToken(request);
        tokenBlacklistService.addTokenToBlacklist(token, tokenBlacklist);
        return new ResponseEntity<>( tokenBlacklist, HttpStatus.FOUND);
    }





}
