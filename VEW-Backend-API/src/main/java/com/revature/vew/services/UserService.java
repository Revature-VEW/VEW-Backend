package com.revature.vew.services;

import com.revature.vew.models.User;
import com.revature.vew.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User registerUser(User newUser){
        return this.userRepository.save(newUser);
    }
}
