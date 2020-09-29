package com.revature.vew.repositories;

import com.revature.vew.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleId(int id);

    Role findByRole(String role);

    boolean existsByRole(String role);
}
