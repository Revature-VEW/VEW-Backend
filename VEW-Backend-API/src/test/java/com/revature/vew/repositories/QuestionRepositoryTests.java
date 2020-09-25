package com.revature.vew.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.revature.vew.models.Question;
import com.revature.vew.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
public class QuestionRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @BeforeEach
    public void setup() {
        User adminUser = userRepository.findUserByEmail("admin@host.com");
        Question question = new Question(adminUser, "How do you write a test?");
        entityManager.persist(question);
    }

    @Test
    public void testFindAll() {
        List<Question> allQuestions = questionRepository.findAll();

        assertThat(allQuestions.size()).isEqualTo(1);
    }

    @Test
    public void testFindRelevantInformationQuestionByQuestionId() {
        // Not happy having to add this in but the question Id seems to change based on the number of methods in this class
        // this ensures that the test does not break due to the ID changing
        List<Question> allQuestions = questionRepository.findAll();
        int idOfFirstQuestion = allQuestions.get(0).getQuestionId();

        Question findRelevantInfoQuestionByQuestionId = questionRepository.findRelevantInfoQuestionByQuestionId(idOfFirstQuestion);

        assertThat(findRelevantInfoQuestionByQuestionId.getUser().getPassword()).isEqualTo(null);
    }
}
