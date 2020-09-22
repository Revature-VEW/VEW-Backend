package com.revature.vew;

import com.revature.vew.models.Role;
import com.revature.vew.models.User;
import com.revature.vew.repositories.RoleRepository;
import com.revature.vew.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.apache.log4j.Logger;

@SpringBootApplication
public class VewApplication {

    private static final Logger log = Logger.getLogger(VewApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(VewApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(RoleRepository roleRepository, UserRepository userRepository) {
        return args -> {
            // save a few roles
            roleRepository.save(new Role(1,"Admin"));
            roleRepository.save(new Role(2,"Instructor"));
            roleRepository.save(new Role(3,"User"));

            // fetch all customers
            log.info("Roles found with findAll():");
            log.info("-------------------------------");
            for (Role role : roleRepository.findAll()) {
                log.info(role.toString());
            }
            log.info("");

            // fetch an individual role by ID
            Role admin = roleRepository.findByRoleId(1);
            Role instructor = roleRepository.findByRoleId(2);
            Role user = roleRepository.findByRoleId(3);
            log.info("Customer found with findByRoleId(1):");
            log.info("--------------------------------");
            log.info(admin.toString());

            // save a few users
            userRepository.save(new User("testone@host.com", "password", "test", "one", admin));
            userRepository.save(new User("testtwo@host.com", "password", "test", "two", instructor));
            userRepository.save(new User("testthree@host.com", "password", "test", "three", user));
            userRepository.save(new User("testfour@host.com", "password", "test", "four", user));
        };
    }
}
