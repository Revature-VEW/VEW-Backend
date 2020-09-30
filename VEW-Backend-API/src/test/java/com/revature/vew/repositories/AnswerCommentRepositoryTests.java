package com.revature.vew.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.revature.vew.models.Answer;
import com.revature.vew.models.AnswerComment;
import com.revature.vew.models.Question;
import com.revature.vew.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
public class AnswerCommentRepositoryTests {
    private TestEntityManager entityManager;
    private UserRepository userRepository;
    private AnswerRepository answerRepository;
    private  AnswerCommentRepository answerCommentRepository;

    @Autowired
    public AnswerCommentRepositoryTests(TestEntityManager entityManager, UserRepository userRepository,
                                        AnswerRepository answerRepository, AnswerCommentRepository answerCommentRepository) {
        this.entityManager = entityManager;
        this.userRepository = userRepository;
        this.answerRepository = answerRepository;
        this.answerCommentRepository = answerCommentRepository;
    }
    @BeforeEach
    public void setup() {
        User adminUser = userRepository.findUserByEmail("admin@host.com");
        Question question = new Question(adminUser, "How do you write a test?");
        entityManager.persist(question);
        Answer answerOne = new Answer(adminUser, question, "Very Carefully");
        entityManager.persist(answerOne);
        Answer answerTwo = new Answer(adminUser, question, "Use the @Test annotation");
        entityManager.persist(answerTwo);
        AnswerComment commentOne = new AnswerComment(adminUser, answerOne, "Funny");
        entityManager.persist(commentOne);
        AnswerComment commentTwo = new AnswerComment(adminUser, answerOne, "Not technically wrong");
        entityManager.persist(commentTwo);
        AnswerComment commentThree = new AnswerComment(adminUser, answerTwo, "More details would be nice");
        entityManager.persist(commentThree);
    }

    @Test
    public void testFindAll() {
        List<AnswerComment> allAnswerComments = answerCommentRepository.findAll();

        assertThat(allAnswerComments.size()).isEqualTo(3);
    }

    @Test
    public void testFindRelevantInfoAnswerCommentByAnswer() {
        Answer answer = answerRepository.findAll().get(0);
        List<AnswerComment> onlyAnswerCommentsForOneAnswer =
                answerCommentRepository.findRelevantInfoAnswerCommentsByAnswer(answer);

        assertThat(onlyAnswerCommentsForOneAnswer.size()).isEqualTo(2);
        assertThat(onlyAnswerCommentsForOneAnswer.get(0).getAnswer().getQuestion()).isEqualTo(null);
    }
}
