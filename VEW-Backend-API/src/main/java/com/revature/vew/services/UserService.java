package com.revature.vew.services;

import com.revature.vew.models.Role;
import com.revature.vew.models.User;
import com.revature.vew.repositories.RoleRepository;
import com.revature.vew.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    // Bcrypt encryption for user password
    BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();

    // this method saves a user to the database
    public User registerUser(User newUser) {
        Role defaultUserRole = this.roleRepository.findRoleByRole("User");
        newUser.setRole(defaultUserRole);
        User filteredUser = new User();
        /* We catch exceptions from the repository methods here
        We than supply a specific userId to let the controller know if a specific exception was thrown
        userId > 0 means a user was created
        userId == -1 means there was a DataIntegrityViolationException
                    ie user(email) already exists
        userId will equal 0 for other exceptions since that is the default value of an int
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

    // this methods checks whether a users email and password match
    public User login(User currentUser) {
        User filteredUser = new User();
        // check to see if user exists with that email
        // if not return user with id -1 so controller can pass message to front end that User does not exist
        if (!this.userRepository.existsByEmail(currentUser.getEmail().toLowerCase())) {
            filteredUser.setUserId(-1);
            return filteredUser;
        }
        User userFromDatabase = this.userRepository.findUserByEmail(currentUser.getEmail().toLowerCase());
        // Use the encrypt.matches method to see if loggin in User (currentUser) matches password from Database
        if (encrypt.matches(currentUser.getPassword(), userFromDatabase.getPassword())) {
            filteredUser.setUserId(userFromDatabase.getUserId());
            filteredUser.setEmail(userFromDatabase.getEmail());
            filteredUser.setFirstName(userFromDatabase.getFirstName());
            filteredUser.setLastName(userFromDatabase.getLastName());
            filteredUser.setRole(userFromDatabase.getRole());
            return filteredUser;
        } else {
            // this will default userId to 0 so controller knows that password was wrong
            return filteredUser;
        }
    }
}
