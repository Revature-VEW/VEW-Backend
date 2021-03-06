package com.revature.vew.repositories;

import com.revature.vew.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUserId(int id);

    User findUserByEmail(String email);

    boolean existsByEmail(String email);
}
