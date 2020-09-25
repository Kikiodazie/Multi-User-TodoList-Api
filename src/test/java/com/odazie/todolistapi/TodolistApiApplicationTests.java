package com.odazie.todolistapi;

import static org.mockito.Mockito.when;

import com.odazie.todolistapi.business.service.UserService;
import com.odazie.todolistapi.data.entity.User;
import com.odazie.todolistapi.data.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class TodolistApiApplicationTests {


    @Test
    void contextLoads() {
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void signupUserTest(){
        User user = new User();
        user.setEmail("admin@gmail.com");
        user.setPassword("admin");//Mocked Data

        when(userRepository.save(user)).thenReturn(user);

        Assert.assertNotNull(user);
    }






}
