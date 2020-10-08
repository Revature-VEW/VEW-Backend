package com.revature.vew.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.revature.vew.models.Answer;
import com.revature.vew.models.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;

@ActiveProfiles("repository") // This sets profile to repository which means the Command Line Runner Bean will be run.
@DataJpaTest
public class AnswerRepositoryTests {
    private AnswerRepository answerRepository;

    @Autowired
    public AnswerRepositoryTests(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Test
    public void testFindAll() {
        List<Answer> allAnswers = answerRepository.findAll();
        assertThat(allAnswers.size()).isEqualTo(3);
    }

    @Test
    public void testFindRelevantInfoAnswersByQuestion() {
        Question question = new Question(2);
        List<Answer> onlyAnswersForOneQuestion = answerRepository.findRelevantInfoAnswersByQuestion(question);
        assertThat(onlyAnswersForOneQuestion.size()).isEqualTo(2);
        assertThat(onlyAnswersForOneQuestion.get(0).getUser().getPassword()).isEqualTo(null);
    }

    @Test
    public void testFindRelevantInfoAnswerByAnswerId() {
        Answer answerFoundById = answerRepository.findRelevantInfoAnswerByAnswerId(1);
        assertThat(answerFoundById.getAnswerId()).isEqualTo(1);
        assertThat(answerFoundById.getAnswer()).isEqualTo("General Purpose Programming Language");
        assertThat(answerFoundById.getUser().getPassword()).isEqualTo(null);
    }
}
