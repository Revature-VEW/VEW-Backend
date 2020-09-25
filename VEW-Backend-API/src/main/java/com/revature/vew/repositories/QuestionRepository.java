package com.revature.vew.repositories;

import com.revature.vew.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    // This method returns a question with ONLY the relevant information needed to display it on the front end
    @Query("SELECT new Question(questionId, question, totalUpvotes, totalDownvotes, creationDate, lastModifiedDate, user.userId, " +
            "user.firstName, user.lastName) from Question where questionId = ?1")
    Question findRelevantInfoQuestionByQuestionId(int id);
}
