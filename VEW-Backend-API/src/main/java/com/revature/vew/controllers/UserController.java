package com.revature.vew.controllers;

import com.revature.vew.models.User;
import com.revature.vew.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.net.URISyntaxException;

@RestController
@CrossOrigin
@RequestMapping(path="/user")
public class UserController {

    // Bcrypt encryption for user password
    BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    // This method takes in a new user and registers it
    // Uses the UserService's registerUser method to achieve this
    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody User newUser) throws URISyntaxException {
        // Will encrypt user password for database security
        newUser.setPassword(encrypt.encode(newUser.getPassword()));

        // sets the email to lowercase to store in the database
        newUser.setEmail(newUser.getEmail().toLowerCase());

        User addedUser = this.userService.registerUser(newUser);
        /* If statements to check what userId was returned by the Service
        userId > 0 means a user was created
        userId == -1 means there was a DataIntegrityViolationException
                    ie user(email) already exists
        */
        if (addedUser.getUserId() > 0) {
            return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
        } else if (addedUser.getUserId() == -1) {
            return new ResponseEntity<>("User Already Exists", HttpStatus.NOT_ACCEPTABLE);
        } else {
            return new ResponseEntity<>("Server Exception", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* This method takes in a users login information and sends it to the userservice
                      to check if user exists and if the password matches
       The UserService login method will return the user if the password matches. This will be a user with id
       higher than 0. If the User does not exist it returns a user with Id -1. If the password does not match it
       returns a user with id 0. The controller than returns the appropriate response entity depending on which userId
       the login method returns.
    */
    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody User currentUser) throws URISyntaxException {
        User userFromService = userService.login(currentUser);
        if (userFromService.getUserId() > 0) {
            return new ResponseEntity<>(userFromService, HttpStatus.ACCEPTED);
        } else if (userFromService.getUserId() == -1) {
            return new ResponseEntity<>("A User with that Email does not exist.", HttpStatus.NOT_ACCEPTABLE);
        } else {
            return new ResponseEntity<>("That password does not match the one on file.", HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
