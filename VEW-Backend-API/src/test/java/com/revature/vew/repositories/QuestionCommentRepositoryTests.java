package com.revature.vew.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.revature.vew.models.Question;
import com.revature.vew.models.QuestionComment;
import com.revature.vew.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
public class QuestionCommentRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private  QuestionCommentRepository questionCommentRepository;

    @BeforeEach
    public void setup() {
        User adminUser = userRepository.findUserByEmail("admin@host.com");
        Question questionOne = new Question(adminUser, "How do you write a test?");
        entityManager.persist(questionOne);
        Question questionTwo = new Question(adminUser, "What is the DQL method?");
        entityManager.persist(questionTwo);
        QuestionComment questionCommentOne = new QuestionComment(adminUser, questionOne, "This needs to be more specific.");
        entityManager.persist(questionCommentOne);
        QuestionComment questionCommentTwo = new QuestionComment(adminUser, questionOne, "This needs to be more specific");
        entityManager.persist(questionCommentTwo);
        QuestionComment questionCommentThree = new QuestionComment(adminUser, questionTwo, "This needs to be more specific");
        entityManager.persist(questionCommentThree);
    }

    @Test
    public void testFindAll() {
        List<QuestionComment> allQuestionComments = questionCommentRepository.findAll();

        assertThat(allQuestionComments.size()).isEqualTo(3);
    }

    @Test
    public void testFindRelevantInfoQuestionCommentsByQuestion() {
        Question question = questionRepository.findAll().get(0);
        List<QuestionComment> onlyCommentsForOneQuestion =
                questionCommentRepository.findRelevantInfoQuestionCommentsByQuestion(question);

        assertThat(onlyCommentsForOneQuestion.size()).isEqualTo(2);
        assertThat(onlyCommentsForOneQuestion.get(0).getUser().getPassword()).isEqualTo(null);
    }
}
