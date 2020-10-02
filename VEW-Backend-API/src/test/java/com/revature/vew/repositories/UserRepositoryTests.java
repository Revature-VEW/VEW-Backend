package com.revature.vew.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.revature.vew.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;

@ActiveProfiles("repository") // This sets profile to repository which means the Command Line Runner Bean will be run.
@DataJpaTest
public class UserRepositoryTests {
    private UserRepository userRepository;

    @Autowired
    public UserRepositoryTests(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        User user = userRepository.findUserByEmail("instructor@host.com");
        assertThat(user.getLastName()).isEqualTo("Approve");
    }

    @Test
    public void testExistsByEmail() {
        boolean doesExist = userRepository.existsByEmail("user@host.com");
        assertThat(doesExist).isEqualTo(true);
    }
}
