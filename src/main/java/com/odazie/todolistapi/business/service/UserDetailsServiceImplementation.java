package com.odazie.todolistapi.business.service;

import com.odazie.todolistapi.data.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImplementation  implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImplementation(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserRepository().findByEmail(email);

        if (user == null){
            throw  new UsernameNotFoundException(email);
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(), Collections.emptyList());

    }
}
