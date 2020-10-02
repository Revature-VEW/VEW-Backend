package com.revature.vew.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.revature.vew.models.AnswerRanking;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;

@ActiveProfiles("repository") // This sets profile to repository which means the Command Line Runner Bean will be run.
@DataJpaTest
public class AnswerRankingRepositoryTests {
    private AnswerRankingRepository answerRankingRepository;

    @Autowired
    public AnswerRankingRepositoryTests(AnswerRankingRepository answerRankingRepository) {
        this.answerRankingRepository = answerRankingRepository;
    }

    @Test
    public void testFindAll() {
        List<AnswerRanking> answerRankings = answerRankingRepository.findAll();
        assertThat(answerRankings.size()).isEqualTo(3);
    }
}
