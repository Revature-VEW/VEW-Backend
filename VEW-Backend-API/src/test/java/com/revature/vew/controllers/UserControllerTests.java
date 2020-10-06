package com.revature.vew.controllers;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.vew.models.Role;
import com.revature.vew.models.User;
import com.revature.vew.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTests {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userServiceMock;

    @Autowired
    public  UserControllerTests(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testRegisterUserWhenServiceReturnsUserWithIdTwo() throws Exception {
        User newUser = new User("testone@host.com", "test", "Test", "One");
        User userWithJustId = new User(2);
        when(userServiceMock.registerUser(any(User.class))).thenReturn(userWithJustId);

        this.mockMvc.perform(post("/user")
                .content(objectMapper.writeValueAsString(newUser))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"userId\":2,\"email\":null,\"password\":null,\"firstName\":null,\"lastName\":null,\"role\":null}"))
                .andReturn();
    }

    @Test
    public void testRegisterUserWhenServiceReturnsUserWithIdNegativeOne() throws Exception {
        User newUser = new User("testone@host.com", "test", "Test", "One");
        User userWithJustId = new User(-1);
        when(userServiceMock.registerUser(any(User.class))).thenReturn(userWithJustId);

        this.mockMvc.perform(post("/user")
                .content(objectMapper.writeValueAsString(newUser))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotAcceptable())
                .andExpect(content().string("User Already Exists"))
                .andReturn();
    }

    @Test
    public void testRegisterUserWhenServiceReturnsUserWithIdZero() throws Exception {
        User newUser = new User("testone@host.com", "test", "Test", "One");
        User userWithJustId = new User(0);
        when(userServiceMock.registerUser(any(User.class))).thenReturn(userWithJustId);

        this.mockMvc.perform(post("/user")
                .content(objectMapper.writeValueAsString(newUser))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Server Exception"))
                .andReturn();
    }

    @Test
    public void testRegisterUserSendsCorrectUserToUserService() throws Exception {
        User newUser = new User("testOne@host.com", "test", "Test", "One");
        User userWithJustId = new User(2);
        when(userServiceMock.registerUser(any(User.class))).thenReturn(userWithJustId);

        this.mockMvc.perform(post("/user")
                .content(objectMapper.writeValueAsString(newUser))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userServiceMock, times(1)).registerUser(userCaptor.capture());
        assertThat(userCaptor.getValue().getPassword()).isNotEqualTo("test");
        assertThat(userCaptor.getValue().getEmail()).isEqualTo("testone@host.com");
    }

    @Test
    public void testLoginWhenUserAndPasswordMatch() throws Exception {
        Role userRole = new Role(3, "User");
        User currentUser = new User("testone@host.com", "password");
        User returnedUser = new User(2, "testone@host.com", "Test", "One", userRole);
        when(userServiceMock.login(any(User.class))).thenReturn(returnedUser);

        this.mockMvc.perform(post("/user/login")
                .content(objectMapper.writeValueAsString(currentUser))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().json("{\"userId\":2,\"email\":\"testone@host.com\",\"password\":null," +
                        "\"firstName\":\"Test\",\"lastName\":\"One\",\"role\":{\"createdBy\":null,\"creationDate\":null," +
                        "\"lastModifiedBy\":null,\"lastModifiedDate\":null,\"roleId\":3,\"role\":\"User\"}}"))
                .andReturn();
    }

    @Test
    public void testLoginWhenUserDoesNotExist() throws Exception {
        User currentUser = new User("testone@host.com", "password");
        User returnedUser = new User(-1);
        when(userServiceMock.login(any(User.class))).thenReturn(returnedUser);

        this.mockMvc.perform(post("/user/login")
                .content(objectMapper.writeValueAsString(currentUser))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotAcceptable())
                .andExpect(content().string("A User with that Email does not exist"))
                .andReturn();
    }

    @Test
    public void testLoginWhenPasswordDoesNotMatch() throws Exception {
        User currentUser = new User("testone@host.com", "password");
        User returnedUser = new User(0);
        when(userServiceMock.login(any(User.class))).thenReturn(returnedUser);

        this.mockMvc.perform(post("/user/login")
                .content(objectMapper.writeValueAsString(currentUser))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotAcceptable())
                .andExpect(content().string("That password does not match the one on file"))
                .andReturn();
    }
}
