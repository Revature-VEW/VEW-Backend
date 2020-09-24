package com.revature.vew;

import com.revature.vew.models.*;
import com.revature.vew.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.apache.log4j.Logger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class VewApplication {

    private static final Logger log = Logger.getLogger(VewApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(VewApplication.class, args);
    }

    // Spring boot’s CommandLineRunner interface is used to run a code block only once in application’s lifetime
    //                  – after application is initialized.
    // We use it to set up some predetermined roles and one master user.
    @Bean
    public CommandLineRunner dbSetup(RoleRepository roleRepository, UserRepository userRepository) {
        return args -> {
            log.info("dbSetup method run");
            // add roles
            Role adminRole = new Role(1, "Admin");
            Role instructorRole = new Role(2, "Instructor");
            Role userRole = new Role(3, "User");
            if (!roleRepository.existsByRole(adminRole.getRole())) {
                roleRepository.save(adminRole);
                log.info("adminRole saved");
            }
            if (!roleRepository.existsByRole(instructorRole.getRole())) {
                roleRepository.save(instructorRole);
                log.info("instructorRole saved");
            }
            if (!roleRepository.existsByRole(userRole.getRole())) {
                roleRepository.save(userRole);
                log.info("userRole saved");
            }

            // Add admin master user
            String password;
            // The following if block is because I did not want to set up Env Variables for each test and
            // could not figure out how to do it for all of them.
            if (System.getenv("admin_password") == null) {
                password = "test";
            } else {
                password = System.getenv("admin_password");
            }
            User adminUser = new User("admin@host.com", password, "Admin", "Power", adminRole);
            if (!userRepository.existsByEmail(adminUser.getEmail())) {
                userRepository.save(adminUser);
                log.info("admin master user saved");
            }

            log.info("dbSetup method finished");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            log.info(dtf.format(now));
        };
    }
}
