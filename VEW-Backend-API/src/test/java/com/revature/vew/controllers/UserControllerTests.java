package com.revature.vew.controllers;

import static org.mockito.Mockito.*;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test") // This sets profile to test which means the Command Line Runner Bean will not be run.
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
    public void registerUserShouldReturnUser() throws Exception {
        Role userRole = new Role(3, "User");
        User newUser = new User("testone@host.com", "test", "Test", "One", userRole);
        User userWithJustId = new User(2);
        when(userServiceMock.registerUser(org.mockito.ArgumentMatchers.any())).thenReturn(userWithJustId);

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
    public void registerUserShouldReturnUserWithIdZero() throws Exception {
        Role userRole = new Role(3, "User");
        User newUser = new User("testone@host.com", "test", "Test", "One", userRole);
        User userWithJustId = new User(0);
        when(userServiceMock.registerUser(org.mockito.ArgumentMatchers.any())).thenReturn(userWithJustId);

        this.mockMvc.perform(post("/user")
                .content(objectMapper.writeValueAsString(newUser))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotAcceptable())
                .andExpect(content().json("{\"userId\":0,\"email\":null,\"password\":null,\"firstName\":null,\"lastName\":null,\"role\":null}"))
                .andReturn();
    }

    @Test
    public void testRegisterUserSendsCorrectUserToUserService() throws Exception {
        Role userRole = new Role(3, "User");
        User newUser = new User("testOne@host.com", "test", "Test", "One", userRole);
        User userWithJustId = new User(0);
        when(userServiceMock.registerUser(org.mockito.ArgumentMatchers.any())).thenReturn(userWithJustId);

        this.mockMvc.perform(post("/user")
                .content(objectMapper.writeValueAsString(newUser))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotAcceptable())
                .andExpect(content().json("{\"userId\":0,\"email\":null,\"password\":null,\"firstName\":null,\"lastName\":null,\"role\":null}"))
                .andReturn();

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userServiceMock, times(1)).registerUser(userCaptor.capture(), eq(true));
        assertThat(userCaptor.getValue().getFirstName()).isEqualTo("Zaphod");
        assertThat(userCaptor.getValue().getEmail()).isEqualTo("zaphod@galaxy.net");
    }
}
