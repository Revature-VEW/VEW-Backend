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
import java.util.Arrays;

@SpringBootApplication
public class VewApplication {

    private static final Logger log = Logger.getLogger(VewApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(VewApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(RoleRepository roleRepository, UserRepository userRepository,
                                  QuestionRepository questionRepository, QuestionRankingRepository questionRankingRepository,
                                  TagRepository tagRepository, AnswerRepository answerRepository,
                                  AnswerCommentRepository answerCommentRepository) {
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

            // fetch question by question Id with only relevant information
            Question relevantInfoQuestion = questionRepository.findRelevantInformationQuestionByQuestionId(1);
            log.info("Question found with findRelevantInformationQuestionByQuestionId(1)");
            log.info("-------------------------------");
            log.info(relevantInfoQuestion.toString());
            log.info("");


            // save a few question rankings
            questionRankingRepository.save(new QuestionRanking(userForQuestions, relevantInfoQuestion, true));

            // fetch all question rankings
            log.info("Question Rankings found with findAll():");
            log.info("-------------------------------");
            for (QuestionRanking tempQuestionRanking : questionRankingRepository.findAll()) {
                log.info(tempQuestionRanking.toString());
            }
            log.info("");

            // save a few tags
            Tag tagJava = new Tag("Java");
            Tag tagCapco = new Tag("Capco");
            Tag tagSpring = new Tag("Spring");
            tagRepository.saveAll(Arrays.asList(tagJava, tagCapco, tagSpring));

            // fetch all tags
            log.info("Tags found with findAll():");
            log.info("-------------------------------");
            for (Tag tempTag : tagRepository.findAll()) {
                log.info(tempTag.toString());
            }
            log.info("");

            // add tags to Question
            relevantInfoQuestion.getTags().addAll(Arrays.asList(tagJava, tagCapco, tagSpring));
            questionRepository.save(relevantInfoQuestion);

            // fetch all questions
            log.info("Questions found with findAll():");
            log.info("-------------------------------");
            for (Question tempQuestion : questionRepository.findAll()) {
                log.info(tempQuestion.toString());
            }
            log.info("");

            // Save a couple Answers
            answerRepository.save(new Answer(userForQuestions, relevantInfoQuestion,"Test Unix"));
            answerRepository.save(new Answer(userForQuestions, relevantInfoQuestion,"Test Linux"));
            answerRepository.save(new Answer(userForQuestions, relevantInfoQuestion,"Test Java"));

            // fetch all answers to Question
            Answer relevantInfoAnswer = answerRepository.findRelevantInformationAnswerByAnswerId(1);
            log.info("Answer found with findRelevantAnswerByAnswerId(1)");
            log.info("-------------------------------");
            log.info(relevantInfoAnswer.toString());
            log.info("");

            // Save a couple answer Comments
            answerCommentRepository.save(new AnswerComment(userForQuestions, relevantInfoAnswer,"This is checking for test Unix"));
            answerCommentRepository.save(new AnswerComment(userForQuestions, relevantInfoAnswer,"This is checking for test Linux"));
            answerCommentRepository.save(new AnswerComment(userForQuestions, relevantInfoAnswer,"This is checking for test Java"));

            // find all answer comments
            log.info("Answer comments found with findAll():");
            log.info("-------------------------------");
            for (AnswerComment tempAnswerComment : answerCommentRepository.findAll()) {
                log.info(tempAnswerComment.toString());
            }
            log.info("");

            // find an answer comment with just relevant information
            AnswerComment relevantInfoComment = answerCommentRepository.findRelevantInformationAnswerCommentByAnswerCommentId(1);
            log.info("Answer found with findRelevantInformationAnswerCommentByAnswerCommentId(1)");
            log.info("-------------------------------");
            log.info(relevantInfoComment.toString());
            log.info("");


            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            log.info(dtf.format(now));
        };
    }
}
