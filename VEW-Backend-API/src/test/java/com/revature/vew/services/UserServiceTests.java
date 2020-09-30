package com.revature.vew.services;

import com.revature.vew.models.User;
import com.revature.vew.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    public void registerUserShouldReturnUser(){
        User newUser = new User();
        newUser.setUserId(1);
        when(userRepository.save(any(User.class))).thenReturn(new User());
        User userCreated = userService.registerUser(newUser);
        Assert.assertEquals(userCreated.getUserId()).isSameAs(newUser.getUserId());
    }

}