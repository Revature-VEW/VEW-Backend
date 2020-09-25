package com.revature.vew.repositories;

import com.revature.vew.models.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RoleRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RoleRepository  roleRepository;

    @Test
    public void testFindAll() {
        List<Role> allRoles = roleRepository.findAll();

        assertThat(allRoles.size()).isEqualTo(3);
    }

    @Test
    public  void testFindByRoleId() {
     Role roleOne = roleRepository.findByRoleId(1);

     assertThat(roleOne.getRoleId()).isEqualTo(1);
    }

    @Test
    public void testExistsByRole() {
        boolean doesExist = roleRepository.existsByRole("Admin");

        assertThat(doesExist).isEqualTo(true);
    }
}
