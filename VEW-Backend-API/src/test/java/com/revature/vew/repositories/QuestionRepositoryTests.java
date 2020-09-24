package com.revature.vew.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.revature.vew.models.Question;
import com.revature.vew.models.Role;
import com.revature.vew.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class QuestionRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private QuestionRepository  questions;

    @Test
    public void testFindRelevantInformationQuestionByQuestionId() {
        Role role = new Role(1, "Admin");
        User user = new User("testfortest@host.com", "password", "Test", "Fortest", role);
        Question question = new Question(user, "How do you write a test?");
        entityManager.persist(question);

        Question findRelevantInformationQuestionByQuestionId = questions.findRelevantInformationQuestionByQuestionId(1);

        assertThat(findRelevantInformationQuestionByQuestionId).extracting("password").isEqualTo(null);
    }
}
