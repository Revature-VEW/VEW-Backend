package com.revature.vew;

import com.revature.vew.models.Question;
import com.revature.vew.models.Role;
import com.revature.vew.models.User;
import com.revature.vew.repositories.QuestionRepository;
import com.revature.vew.repositories.RoleRepository;
import com.revature.vew.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.apache.log4j.Logger;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class VewApplication {

    private static final Logger log = Logger.getLogger(VewApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(VewApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(RoleRepository roleRepository, UserRepository userRepository,
                                  QuestionRepository questionRepository) {
        return args -> {
            // save a few roles
            roleRepository.save(new Role(1,"Admin"));
            roleRepository.save(new Role(2,"Instructor"));
            roleRepository.save(new Role(3,"User"));

            // fetch all roles
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
            log.info("Role found with findByRoleId(1):");
            log.info("--------------------------------");
            log.info(admin.toString());

            // save a few users
            userRepository.save(new User("testone@host.com", "password", "test", "one", admin));
            userRepository.save(new User("testtwo@host.com", "password", "test", "two", instructor));
            userRepository.save(new User("testthree@host.com", "password", "test", "three", user));
            userRepository.save(new User("testfour@host.com", "password", "test", "four", user));

            // fetch all users
            log.info("Users found with findAll():");
            log.info("-------------------------------");
            for (User tempUser : userRepository.findAll()) {
                log.info(tempUser.toString());
            }
            log.info("");

            // fetch an individual User by Id
            User userForQuestions = userRepository.findUserByUserId(1);
            log.info("User found with findUserByUserId(1):");
            log.info("--------------------------------");
            log.info(userForQuestions.toString());

            // save a few questions
            questionRepository.save(new Question(userForQuestions, "What is Unix?"));
            questionRepository.save(new Question(userForQuestions, "What is Linux?"));
            questionRepository.save(new Question(userForQuestions, "What is Java?"));

            // fetch all questions
            log.info("Questions found with findAll():");
            log.info("-------------------------------");
            for (Question tempQuestion : questionRepository.findAll()) {
                log.info(tempQuestion.toString());
            }
            log.info("");

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            log.info(dtf.format(now));
        };
    }
}
