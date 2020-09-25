package com.revature.vew.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.revature.vew.models.Answer;
import com.revature.vew.models.Question;
import com.revature.vew.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
public class AnswerRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @BeforeEach
    public void setup() {
        User adminUser = userRepository.findUserByEmail("admin@host.com");
        Question questionOne = new Question(adminUser, "How do you write a test?");
        entityManager.persist(questionOne);
        Question questionTwo = new Question(adminUser, "What is the DQL method?");
        entityManager.persist(questionTwo);
        Answer answerOne = new Answer(adminUser, questionOne, "Very Carefully");
        entityManager.persist(answerOne);
        Answer answerTwo = new Answer(adminUser, questionOne, "Use the @Test annotation");
        entityManager.persist(answerTwo);
        Answer answerThree = new Answer(adminUser, questionTwo, "SELECT");
        entityManager.persist(answerThree);
    }

    @Test
    public void testFindAll() {
        List<Answer> allAnswers = answerRepository.findAll();

        assertThat(allAnswers.size()).isEqualTo(3);
    }

    @Test
    public void testFindRelevantInfoAnswersByQuestion() {
        Question question = questionRepository.findAll().get(0);
        List<Answer> onlyAnswersForOneQuestion = answerRepository.findRelevantInfoAnswersByQuestion(question);

        assertThat(onlyAnswersForOneQuestion.size()).isEqualTo(2);
        assertThat(onlyAnswersForOneQuestion.get(0).getUser().getPassword()).isEqualTo(null);
    }
}
