package com.revature.vew.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.revature.vew.models.Question;
import com.revature.vew.models.QuestionComment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;

@ActiveProfiles("repository") // This sets profile to repository which means the Command Line Runner Bean will be run.
@DataJpaTest
public class QuestionCommentRepositoryTests {
    private  QuestionCommentRepository questionCommentRepository;

    @Autowired
    public QuestionCommentRepositoryTests(QuestionCommentRepository questionCommentRepository) {
        this.questionCommentRepository = questionCommentRepository;
    }

    @Test
    public void testFindAll() {
        List<QuestionComment> allQuestionComments = questionCommentRepository.findAll();
        assertThat(allQuestionComments.size()).isEqualTo(3);
    }

    @Test
    public void testFindRelevantInfoQuestionCommentsByQuestion() {
        Question question = new Question(1);
        List<QuestionComment> onlyCommentsForOneQuestion =
                questionCommentRepository.findRelevantInfoQuestionCommentsByQuestion(question);

        assertThat(onlyCommentsForOneQuestion.size()).isEqualTo(2);
        assertThat(onlyCommentsForOneQuestion.get(0).getUser().getPassword()).isEqualTo(null);
    }
}
