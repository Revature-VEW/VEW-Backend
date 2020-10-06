package com.revature.vew;

import com.revature.vew.models.*;
import com.revature.vew.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Profile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@SpringBootApplication
@EnableSwagger2
public class VewApplication {

    private static final Logger log = Logger.getLogger(VewApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(VewApplication.class, args);
    }

    // Spring boot’s CommandLineRunner interface is used to run a code block only once in application’s lifetime
    //                  – after application is initialized.
    // We use it to set populate the database for our repository tests instead of using SQL
    @Profile("repository") // Runs this bean whenever the Profile is repository
    @Bean
    public CommandLineRunner repositoryTestDBSetup(RoleRepository roleRepository, UserRepository userRepository,
                                                   QuestionRepository questionRepository, QuestionRankingRepository questionRankingRepository,
                                                   QuestionCommentRepository questionCommentRepository, TagRepository tagRepository,
                                                   AnswerRepository answerRepository, AnswerRankingRepository answerRankingRepository,
                                                   AnswerCommentRepository answerCommentRepository) {
        return args -> {
            log.info("dbSetup method run");
            // Add roles
            log.info("adding Roles");
            Role adminRole = new Role(1, "Admin");
            Role instructorRole = new Role(2, "Instructor");
            Role userRole = new Role(3, "User");
            roleRepository.saveAll(Arrays.asList(adminRole, instructorRole, userRole));

            // Add users
            log.info("adding Users");
            User adminUser = new User("admin@host.com", "password", "Admin", "Power", adminRole);
            User instructorUser = new User("instructor@host.com", "password", "Instructor", "Approve", instructorRole);
            User normalUser = new User("user@host.com", "password", "User", "None", userRole);
            userRepository.saveAll(Arrays.asList(adminUser, instructorUser, normalUser));

            // Add Questions
            log.info("adding Questions");
            Question javaQuestion = new Question(adminUser, "What is Java?");
            Question springQuestion = new Question(adminUser, "What is Spring?");
            Question angularQuestion = new Question(instructorUser, "What is Angular?");
            questionRepository.saveAll(Arrays.asList(javaQuestion, springQuestion, angularQuestion));

            // Add Question Rankings
            log.info("adding Question Rankings");
            QuestionRanking rankingOne = new QuestionRanking(adminUser, javaQuestion, true);
            QuestionRanking rankingTwo = new QuestionRanking(instructorUser, javaQuestion, true);
            QuestionRanking rankingThree = new QuestionRanking(normalUser, javaQuestion, false);
            questionRankingRepository.saveAll(Arrays.asList(rankingOne, rankingTwo, rankingThree));

            // Add Question Comments
            QuestionComment questionCommentOne = new QuestionComment(adminUser, javaQuestion, "Good question everyone should know this.");
            QuestionComment questionCommentTwo = new QuestionComment(instructorUser, javaQuestion, "Know this by heart.");
            QuestionComment questionCommentThree = new QuestionComment(normalUser, angularQuestion, "Angular is my favorite language.");
            questionCommentRepository.saveAll(Arrays.asList(questionCommentOne, questionCommentTwo, questionCommentThree));

            // Add Tags
            log.info("adding Tags");
            Tag java = new Tag("Java");
            Tag angular = new Tag("Angular");
            Tag capco = new Tag("capco");
            tagRepository.saveAll(Arrays.asList(java, angular, capco));

            // Add Answers
            log.info("adding Answers");
            Answer javaAnswer = new Answer(adminUser, javaQuestion, "General Purpose Programming Language");
            Answer springAnswerOne = new Answer(instructorUser, springQuestion, "Framework to speed up Java projects.");
            Answer springAnswerTwo = new Answer(adminUser, springQuestion, "Developed by Pivotal");
            answerRepository.saveAll(Arrays.asList(javaAnswer, springAnswerOne, springAnswerTwo));

            // Add Answer Rankings
            log.info("adding Answer Rankings");
            AnswerRanking answerRankingOne = new AnswerRanking(adminUser, javaAnswer, true);
            AnswerRanking answerRankingTwo = new AnswerRanking(instructorUser, javaAnswer, true);
            AnswerRanking answerRankingThree = new AnswerRanking(normalUser, javaAnswer, false);
            answerRankingRepository.saveAll(Arrays.asList(answerRankingOne, answerRankingTwo, answerRankingThree));

            // Add Answer Comments
            log.info("adding Answer Comments");
            AnswerComment answerCommentOne = new AnswerComment(normalUser, springAnswerTwo, "I did not know this.");
            AnswerComment answerCommentTwo = new AnswerComment(adminUser, springAnswerTwo, "True but, other things about Spring are more important.");
            AnswerComment answerCommentThree = new AnswerComment(instructorUser, javaAnswer, "True but, should expand on this answer.");
            answerCommentRepository.saveAll(Arrays.asList(answerCommentOne, answerCommentTwo, answerCommentThree));


            log.info("dbSetup method finished");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            log.info(dtf.format(now));
        };
    }
}
