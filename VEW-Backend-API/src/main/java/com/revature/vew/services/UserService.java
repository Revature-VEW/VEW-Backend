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
        Role defaultUserRole = this.roleRepository.findRoleByRole("User");
        newUser.setRole(defaultUserRole);
        User filteredUser = new User();
        /* We catch exceptions from the repository methods here
        We than supply a specific userId to let the controller know if a specific exception was thrown
        userId > 0 means a user was created
        userId == -1 means there was a DataIntegrityViolationException
                    ie user(email) already exists
        userId will equal 0 for other exceptions since that is the defatul value of an int
        */
        try {
            User unfilteredUser = this.userRepository.save(newUser);
            filteredUser.setUserId(unfilteredUser.getUserId());
        } catch (DataIntegrityViolationException ex) {
            System.out.println(ex);
            filteredUser.setUserId(-1);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return filteredUser;
    }
}
