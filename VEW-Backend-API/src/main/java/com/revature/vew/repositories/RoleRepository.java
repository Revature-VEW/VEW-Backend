package com.revature.vew.repositories;

import com.revature.vew.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleByRoleId(int id);

    Role findRoleByRole(String role);

    boolean existsByRole(String role);
}
