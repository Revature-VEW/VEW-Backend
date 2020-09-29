package com.revature.vew.controllers;

import com.revature.vew.models.User;
import com.revature.vew.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping
    public User registerUser(@RequestBody User newUser) throws URISyntaxException {
        // Will encrypt user password for database security
        newUser.setPassword(encrypt.encode(newUser.getPassword()));

        return this.userService.registerUser(newUser);
    }


}
