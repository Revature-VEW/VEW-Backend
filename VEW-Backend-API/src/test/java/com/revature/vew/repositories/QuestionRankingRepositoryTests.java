package com.revature.vew.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.revature.vew.models.QuestionRanking;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;

@ActiveProfiles("repository") // This sets profile to repository which means the Command Line Runner Bean will be run.
@DataJpaTest
public class QuestionRankingRepositoryTests {
    private QuestionRankingRepository questionRankingRepository;

    @Autowired
    public QuestionRankingRepositoryTests(QuestionRankingRepository questionRankingRepository) {
        this.questionRankingRepository = questionRankingRepository;
    }

    @Test
    public void testFindAll() {
        List<QuestionRanking> questionRankings = questionRankingRepository.findAll();
        assertThat(questionRankings.size()).isEqualTo(3);
    }
}
