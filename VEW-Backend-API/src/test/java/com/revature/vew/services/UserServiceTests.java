package com.revature.vew.services;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.revature.vew.models.Role;
import com.revature.vew.models.User;
import com.revature.vew.repositories.RoleRepository;
import com.revature.vew.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private RoleRepository roleRepositoryMock;

    @InjectMocks
    private UserService userServiceMock;

    @Before
    public void init() {
        Role userRole = new Role(3, "User");
        when(roleRepositoryMock.findRoleByRole(userRole.getRole())).thenReturn(userRole);

        User outputtedUser = new User(2, "testemail@host.com", "password", "FirstName", "LastName", userRole);
        when(userRepositoryMock.save(any(User.class))).thenReturn(outputtedUser);


    }

    @Test
    public void registerUserShouldReturnJustUserWithId() {
        User inputtedUser = new User("testemail@host.com", "password", "FirstName", "LastName");
        User userCreated = userServiceMock.registerUser(inputtedUser);
        assertThat(userCreated.getUserId()).isEqualTo(2);
        assertThat(userCreated.getPassword()).isEqualTo(null);
    }

    @Test
    public void testRegisterUserAddsRole() {
        User inputtedUser = new User("testemail@host.com", "password", "FirstName", "LastName");
        userServiceMock.registerUser(inputtedUser);
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepositoryMock, times(1)).save(userCaptor.capture());
        assertThat(userCaptor.getValue().getRole().getRole()).isEqualTo("User");
    }

    @Test
    public void testRegisterUserReturnsUserWithIdNegativeOne() {
        User inputtedUser = new User("testemail@host.com", "password", "FirstName", "LastName");
        when(userRepositoryMock.save(any(User.class))).thenThrow(DataIntegrityViolationException.class);
        User userCreated = userServiceMock.registerUser(inputtedUser);
        assertThat(userCreated.getUserId()).isEqualTo(-1);
        assertThat(userCreated.getPassword()).isEqualTo(null);
    }

    @Test
    public void testRegisterUserReturnsUserWithIdZero() {
        User inputtedUser = new User("testemail@host.com", "password", "FirstName", "LastName");
        when(userRepositoryMock.save(any(User.class))).thenThrow(NullPointerException.class);
        User userCreated = userServiceMock.registerUser(inputtedUser);
        assertThat(userCreated.getUserId()).isEqualTo(0);
        assertThat(userCreated.getPassword()).isEqualTo(null);
    }

    @Test
    public void testLoginReturnsUserIfUserExistsAndPasswordMatches() {
        // Bcrypt encryption for user password
        BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();
        String password = encrypt.encode("password");
        Role userRole = new Role(3, "User");
        User currentUser = new User("testone@host.com", "password");
        User returnedUser = new User(2, "testone@host.com", password, "Test", "One", userRole);
        when(userRepositoryMock.existsByEmail(currentUser.getEmail())).thenReturn(true);
        when(userRepositoryMock.findUserByEmail(currentUser.getEmail())).thenReturn(returnedUser);

        User userFromLogin = userServiceMock.login(currentUser);
        assertThat(userFromLogin.getUserId()).isEqualTo(2);
        assertThat(userFromLogin.getFirstName()).isEqualTo("Test");
    }

    @Test
    public void testLoginReturnsUserWithIdOfNegativeOneIfUserDoesNotExist() {
        User currentUser = new User("testone@host.com", "password");
        when(userRepositoryMock.existsByEmail(currentUser.getEmail())).thenReturn(false);

        User userFromLogin = userServiceMock.login(currentUser);
        assertThat(userFromLogin.getUserId()).isEqualTo(-1);
        assertThat(userFromLogin.getFirstName()).isEqualTo(null);
    }

    @Test
    public void testLoginReturnsUserWithIdZeroIfPasswordIsWrong() {
        // Bcrypt encryption for user password
        BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();
        String password = encrypt.encode("notPassword");
        Role userRole = new Role(3, "User");
        User currentUser = new User("testone@host.com", "password");
        User returnedUser = new User(2, "testone@host.com", password, "Test", "One", userRole);
        when(userRepositoryMock.existsByEmail(currentUser.getEmail())).thenReturn(true);
        when(userRepositoryMock.findUserByEmail(currentUser.getEmail())).thenReturn(returnedUser);

        User userFromLogin = userServiceMock.login(currentUser);
        assertThat(userFromLogin.getUserId()).isEqualTo(0);
        assertThat(userFromLogin.getFirstName()).isEqualTo(null);
    }
}
