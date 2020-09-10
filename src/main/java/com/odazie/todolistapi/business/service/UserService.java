package com.odazie.todolistapi.business.service;

import com.odazie.todolistapi.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserRepository getUserRepository() {
        return userRepository;
    }
}
