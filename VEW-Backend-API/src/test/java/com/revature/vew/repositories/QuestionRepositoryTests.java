package com.revature.vew.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.revature.vew.models.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;

@ActiveProfiles("repository") // This sets profile to repository which means the Command Line Runner Bean will be run.
@DataJpaTest
public class QuestionRepositoryTests {
    private QuestionRepository questionRepository;

    @Autowired
    public QuestionRepositoryTests(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Test
    public void testFindAll() {
        List<Question> allQuestions = questionRepository.findAll();
        assertThat(allQuestions.size()).isEqualTo(3);
    }

    @Test
    public void testFindRelevantInformationQuestionByQuestionId() {
        Question findRelevantInfoQuestionByQuestionId = questionRepository.findRelevantInfoQuestionByQuestionId(1);
        assertThat(findRelevantInfoQuestionByQuestionId.getUser().getPassword()).isEqualTo(null);
    }
}
