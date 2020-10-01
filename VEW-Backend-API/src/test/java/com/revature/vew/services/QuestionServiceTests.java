package com.revature.vew.services;

import com.revature.vew.models.Question;
import com.revature.vew.models.Role;
import com.revature.vew.models.User;
import com.revature.vew.repositories.QuestionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test") // This sets profile to test which means the Command Line Runner Bean will not be run.
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionServiceTests {

    @Mock
    private QuestionRepository questionRepositoryMock;

    @InjectMocks
    private QuestionService questionServiceMock;

    @Test
    public void testAddQuestionReturnsQuestion(){
        Role userRole = new Role(3, "User");
        User user = new User(2, "testemail@host.com", "password", "FirstName", "LastName", userRole);
        Question outputQuestion = new Question(3, user, "What is Dakota?", false, 10, 3);

        when(questionRepositoryMock.save(any(Question.class))).thenReturn(outputQuestion);
        Question inputQuestion = new Question(2, "What is Dakota?");
        Question questionCreated = questionRepositoryMock.save(inputQuestion);
        assertThat(questionCreated).isEqualTo(outputQuestion);
    }
}
