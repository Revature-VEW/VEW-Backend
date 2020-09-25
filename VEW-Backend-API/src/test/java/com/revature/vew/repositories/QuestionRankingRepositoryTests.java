package com.revature.vew.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.revature.vew.models.Question;
import com.revature.vew.models.QuestionRanking;
import com.revature.vew.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
public class QuestionRankingRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRankingRepository questionRankingRepository;

    @BeforeEach
    private void setup() {
        User adminUser = userRepository.findUserByEmail("admin@host.com");
        Question question = new Question(adminUser, "How do you write a test?");
        entityManager.persist(question);
        QuestionRanking questionRanking = new QuestionRanking(adminUser, question, true);
        entityManager.persist(questionRanking);
    }

    @Test
    public void testFindAll() {
        List<QuestionRanking> questionRankings = questionRankingRepository.findAll();

        assertThat(questionRankings.size()).isEqualTo(1);
    }
}
