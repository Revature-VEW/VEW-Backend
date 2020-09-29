package com.revature.vew.services;

import com.revature.vew.models.Role;
import com.revature.vew.models.User;
import com.revature.vew.repositories.RoleRepository;
import com.revature.vew.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    public User registerUser(User newUser) {
        Role defaultUserRole = this.roleRepository.findByRole("User");
        newUser.setRole(defaultUserRole);
        User filteredUser;
        try {
            User unfilteredUser = this.userRepository.save(newUser);
            filteredUser = new User(unfilteredUser.getUserId());
        } catch (DataIntegrityViolationException ex) {
            System.out.println(ex);
            filteredUser = new User(0);
        }

        return filteredUser;
    }
}
