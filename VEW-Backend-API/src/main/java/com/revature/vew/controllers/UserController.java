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
    public ResponseEntity<User> registerUser(@RequestBody User newUser) throws URISyntaxException {
        // Will encrypt user password for database security
        newUser.setPassword(encrypt.encode(newUser.getPassword()));

        // sets the email to lowercase to store in the database
        newUser.setEmail(newUser.getEmail().toLowerCase());

        User addedUser = this.userService.registerUser(newUser);
        if (addedUser.getUserId() > 0) {
            return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(addedUser, HttpStatus.NOT_ACCEPTABLE);
        }
    }


}
