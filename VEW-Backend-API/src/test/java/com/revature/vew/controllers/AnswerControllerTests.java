package com.revature.vew.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.vew.models.Answer;
import com.revature.vew.models.Question;
import com.revature.vew.services.AnswerService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebMvcTest(controllers = AnswerController.class)
public class AnswerControllerTests {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @MockBean
    private AnswerService answerServiceMock;

    @Autowired
    public AnswerControllerTests(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testAddAnswerReturnsAnswerAndResponse() throws Exception {
        Date creationDate = new Date(1L);
        Date updateDate = creationDate;
        Answer outputAnswer = new Answer(1, "The best.", 0, 0, creationDate,
                updateDate, 13, 5, "Test", "One");
        when(answerServiceMock.addAnswer(any(Answer.class))).thenReturn(outputAnswer);

        Answer newAnswer = new Answer("The best.", 13, 5, "Test", "One");
        MvcResult result = this.mockMvc.perform(post("/answer")
                .content(objectMapper.writeValueAsString(newAnswer))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).contains("The best.");
    }

    @Test
    public void testGetAnswerByAnswerIdReturns() throws Exception {
        Date creationDate = new Date(1L);
        Date updateDate = creationDate;
        Answer outputAnswer = new Answer(1, "The best.", 0, 0, creationDate,
                updateDate, 13, 5, "Test", "One");
        when(answerServiceMock.getAnswerByAnswerId(1)).thenReturn(outputAnswer);

        MvcResult result = this.mockMvc.perform(get("/answer/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertNotNull(result);
        assertThat(result.getResponse().getContentAsString()).contains("The best.");
    }

    @Test
    public void testGetAnswerByQuestion() throws Exception {
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
        when(answerServiceMock.getAnswersByQuestion(any(Question.class))).thenReturn(outputtedAnswers);

        MvcResult result = this.mockMvc.perform(get("/answer/question/13"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertNotNull(result);
        assertThat(result.getResponse().getContentAsString()).contains("The best.");
        assertThat(result.getResponse().getContentAsString()).contains("The worst.");
    }
}
