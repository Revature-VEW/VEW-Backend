package com.revature.vew.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.vew.models.Question;
import com.revature.vew.services.QuestionService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.util.Date;

@WebMvcTest(controllers = QuestionController.class)
public class QuestionControllerTests {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @MockBean
    private QuestionService questionServiceMock;

    @Autowired
    public QuestionControllerTests(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testAddQuestionReturnsQuestionAndCreatedResponse() throws Exception {
        Date creationDate = new Date(1L);
        Date updateDate = creationDate;
        Question outputQuestion = new Question(3, "What is Dakota?", 0, 0,
                creationDate, updateDate, 1);
        when(questionServiceMock.addQuestion(any(Question.class))).thenReturn(outputQuestion);

        Question newQuestion = new Question(1, "Why is Dakota?");
        this.mockMvc.perform(post("/question")
                .content(objectMapper.writeValueAsString(newQuestion))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"createdBy\":null,\"creationDate\":\"1970-01-01T00:00:00.001+00:00\"," +
                        "\"lastModifiedBy\":null,\"lastModifiedDate\":\"1970-01-01T00:00:00.001+00:00\",\"questionId\":3," + "" +
                        "\"user\":{\"userId\":1,\"email\":null,\"password\":null,\"firstName\":null,\"lastName\":null,\"role\":null}," + "" +
                        "\"question\":\"What is Dakota?\",\"approved\":false,\"totalUpvotes\":0,\"totalDownvotes\":0,\"tags\":[]}"))
                .andReturn();
    }

    @Test
    public void testGetQuestionByQuestionIdReturnsRelevantInfoQuestion() throws Exception {
        Date creationDate = new Date(1L);
        Date updateDate = creationDate;
        Question outputQuestion = new Question(3, "What is Dakota?", 10, 3,
                creationDate, updateDate, 1, "Adimn", "Power");
        when(questionServiceMock.getQuestionByQuestionId(3)).thenReturn(outputQuestion);

        int id = 3;
        MvcResult result = this.mockMvc.perform(get("/question/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertNotNull(result);
        assertThat(result.getResponse().getContentAsString()).contains("What is Dakota?");
    }
}
