package com.revature.vew.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.revature.vew.models.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;

@ActiveProfiles("repository") // This sets profile to repository which means the Command Line Runner Bean will be run.
@DataJpaTest
public class RoleRepositoryTests {
    private RoleRepository  roleRepository;

    @Autowired
    public RoleRepositoryTests(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Test
    public void testFindAll() {
        List<Role> allRoles = roleRepository.findAll();
        assertThat(allRoles.size()).isEqualTo(3);
    }

    @Test
    public void testFindRoleByRoleId() {
     Role roleOne = roleRepository.findRoleByRoleId(1);
     assertThat(roleOne.getRoleId()).isEqualTo(1);
    }

    @Test
    public void testFindRoleByRole() {
        Role userRole = roleRepository.findRoleByRole("User");
        assertThat(userRole.getRole()).isEqualTo("User");
    }

    @Test
    public void testExistsByRole() {
        boolean doesExist = roleRepository.existsByRole("Admin");
        assertThat(doesExist).isEqualTo(true);
    }
}
