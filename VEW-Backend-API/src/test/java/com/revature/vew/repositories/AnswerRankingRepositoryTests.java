package com.revature.vew.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.revature.vew.models.Answer;
import com.revature.vew.models.AnswerRanking;
import com.revature.vew.models.Question;
import com.revature.vew.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
public class AnswerRankingRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnswerRankingRepository answerRankingRepository;

    @BeforeEach
    private void setup() {
        User adminUser = userRepository.findUserByEmail("admin@host.com");
        Question question = new Question(adminUser, "How do you write a test?");
        entityManager.persist(question);
        Answer answer = new Answer(adminUser, question, "Very Carefully");
        entityManager.persist(answer);
        AnswerRanking answerRankingOne = new AnswerRanking(adminUser, answer, true);
        entityManager.persist(answerRankingOne);
    }

    @Test
    public void testFindAll() {
        List<AnswerRanking> answerRankings = answerRankingRepository.findAll();

        assertThat(answerRankings.size()).isEqualTo(1);
    }
}
