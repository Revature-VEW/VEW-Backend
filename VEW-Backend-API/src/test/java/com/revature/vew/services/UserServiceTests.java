package com.revature.vew.services;

import com.revature.vew.models.Role;
import com.revature.vew.models.User;
import com.revature.vew.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {

    @Mock
    UserRepository userRepositoryMock;

    @InjectMocks
    UserService userServiceMock;

    @Test
    public void registerUserShouldReturnUser(){
        Role userRole = new Role(3, "User");
        User newUser = new User("testemail@host.com", "password", "FirstName", "LastName", userRole);
        newUser.setUserId(1);
        when(userRepositoryMock.save(any(User.class))).thenReturn(newUser);
        User userCreated = userServiceMock.registerUser(newUser);
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userServiceMock, times(1)).save(userCaptor.capture(), eq(true));
        assertThat(userCreated.getUserId()).isSameAs(newUser.getUserId());


    }

}