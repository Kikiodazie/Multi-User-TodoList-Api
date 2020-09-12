package com.odazie.todolistapi.business.service;

import com.odazie.todolistapi.data.entity.User;
import com.odazie.todolistapi.data.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByEmail(String userEmail){
        return getUserRepository().findByEmail(userEmail);
    }





    public UserRepository getUserRepository() {
        return userRepository;
    }
}
