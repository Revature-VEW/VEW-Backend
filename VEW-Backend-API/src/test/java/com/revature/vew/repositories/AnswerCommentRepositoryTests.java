package com.revature.vew.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.revature.vew.models.Answer;
import com.revature.vew.models.AnswerComment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;

@ActiveProfiles("repository") // This sets profile to repository which means the Command Line Runner Bean will be run.
@DataJpaTest
public class AnswerCommentRepositoryTests {
    private  AnswerCommentRepository answerCommentRepository;

    @Autowired
    public AnswerCommentRepositoryTests(AnswerCommentRepository answerCommentRepository) {
        this.answerCommentRepository = answerCommentRepository;
    }

    @Test
    public void testFindAll() {
        List<AnswerComment> allAnswerComments = answerCommentRepository.findAll();
        assertThat(allAnswerComments.size()).isEqualTo(3);
    }

    @Test
    public void testFindRelevantInfoAnswerCommentByAnswer() {
        Answer answer = new Answer(3);
        List<AnswerComment> onlyAnswerCommentsForOneAnswer =
                answerCommentRepository.findRelevantInfoAnswerCommentsByAnswer(answer);
        assertThat(onlyAnswerCommentsForOneAnswer.size()).isEqualTo(2);
        assertThat(onlyAnswerCommentsForOneAnswer.get(0).getAnswer().getQuestion()).isEqualTo(null);
    }
}
