package com.revature.vew.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.revature.vew.models.Role;
import com.revature.vew.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
public class UserRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    public void setup() {
        Role trainerRole = roleRepository.findRoleByRoleId(2);
        Role userRole = roleRepository.findRoleByRoleId(3);
        User trainerUser = new User("testtrainer@host.com", "test", "test", "trainer", trainerRole);
        entityManager.persist(trainerUser);
        User userUser = new User("testuser@host.com", "test", "test", "user", userRole);
        entityManager.persist(userUser);
    }

    @Test
    public void testFindAll() {
        List<User> allUsers = userRepository.findAll();

        assertThat(allUsers.size()).isEqualTo(3);
    }

    @Test
    public void testFindUserByUserId() {
        User user = userRepository.findUserByUserId(1);

        assertThat(user.getLastName()).isEqualTo("Power");
    }

    @Test
    public void testFindUserByEmail() {
        User user = userRepository.findUserByEmail("testtrainer@host.com");

        assertThat(user.getLastName()).isEqualTo("trainer");
    }

    @Test
    public void testExistsByEmail() {
        boolean doesExist = userRepository.existsByEmail("testuser@host.com");

        assertThat(doesExist).isEqualTo(true);
    }
}
