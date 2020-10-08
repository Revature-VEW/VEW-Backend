package com.revature.vew.services;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;

import com.revature.vew.models.Answer;
import com.revature.vew.models.Question;
import com.revature.vew.models.Role;
import com.revature.vew.models.User;
import com.revature.vew.repositories.AnswerRepository;
import com.revature.vew.repositories.QuestionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnswerServiceTests {

    @Mock
    private AnswerRepository answerRepositoryMock;

    @InjectMocks
    private AnswerService answerServiceMock;

    @Test
    public void testAddQuestionReturnsQuestion() {
        Date creationDate = new Date(1L);
        Date updateDate = creationDate;
        Answer outputAnswer = new Answer(1, "The best.", 0, 0, creationDate,
                updateDate, 13, 5, "Test", "One");
        when(answerRepositoryMock.save(any(Answer.class))).thenReturn(outputAnswer);

        Answer newAnswer = new Answer("The best.", 13, 5, "Test", "One");
        Answer answerCreated = answerServiceMock.addAnswer(newAnswer);
        assertThat(answerCreated).isEqualTo(outputAnswer);
    }

    @Test
    public void testGetAnswerByAnswerId() {
        Date creationDate = new Date(1L);
        Date updateDate = creationDate;
        Answer outputAnswer = new Answer(1, "The best.", 0, 0, creationDate,
                updateDate, 13, 5, "Test", "One");
        when(answerRepositoryMock.findRelevantInfoAnswerByAnswerId(1)).thenReturn(outputAnswer);

        Answer answerCreated = answerServiceMock.getAnswerByAnswerId(1);
        assertThat(answerCreated).isEqualTo(outputAnswer);
    }
}
