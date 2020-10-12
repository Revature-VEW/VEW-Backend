package com.revature.vew.services;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;

import com.revature.vew.models.Answer;
import com.revature.vew.models.Question;
import com.revature.vew.repositories.AnswerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Test
    public void testGetAnswersByQuestion() {
        Date creationDate = new Date(1L);
        Date updateDate = creationDate;
        Answer outputAnswerOne = new Answer(1, "The best.", 0, 0, creationDate,
                updateDate, 13, 5, "Test", "One");
        Answer outputAnswerTwo = new Answer(2, "The worst.", 0, 0, creationDate,
                updateDate, 13, 6, "Test", "Two");
        List<Answer> outputtedAnswers = new ArrayList<>();
        outputtedAnswers.add(outputAnswerOne);
        outputtedAnswers.add(outputAnswerTwo);
        Question inputQuestion = new Question(13);
        when(answerRepositoryMock.findRelevantInfoAnswersByQuestion(inputQuestion)).thenReturn(outputtedAnswers);

        List<Answer> answersReturned = answerServiceMock.getAnswersByQuestion(inputQuestion);
        assertThat(answersReturned).isEqualTo(outputtedAnswers);
        assertThat(answersReturned.size()).isEqualTo(2);
    }
}
