package com.revature.vew.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.revature.vew.models.Question;
import com.revature.vew.models.Role;
import com.revature.vew.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@TestPropertySource(properties = {
        "spring.datasource.platform=h2",
        "spring.jpa.database-platform=org.hibernate.dialect.PostgreSQL95Dialect",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
public class QuestionRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository users;

    @Autowired
    private QuestionRepository  questions;

    @Test
    public void testFindRelevantInformationQuestionByQuestionId() {
        User adminUser = users.findUserByEmail("admin@host.com");
        Question question = new Question(adminUser, "How do you write a test?");
        entityManager.persist(question);

        Question findRelevantInformationQuestionByQuestionId = questions.findRelevantInformationQuestionByQuestionId(1);

        assertThat(findRelevantInformationQuestionByQuestionId.getUser().getPassword()).isEqualTo(null);
    }
}
