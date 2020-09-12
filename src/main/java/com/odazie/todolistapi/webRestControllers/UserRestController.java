package com.odazie.todolistapi.webRestControllers;

import com.odazie.todolistapi.business.service.UserService;
import com.odazie.todolistapi.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class UserRestController {

    private final UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserRestController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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




}
