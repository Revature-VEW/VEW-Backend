package com.revature.vew.repositories;

import com.revature.vew.models.Answer;
import com.revature.vew.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    // This method returns an answer by Id
    //      with ONLY the relevant information needed to display it on the front end
    @Query("SELECT new Answer (answerId, answer, totalUpvotes, totalDownvotes, creationDate, lastModifiedDate, " +
            "question.questionId, user.userId, user.firstName, user.lastName) from Answer where answerId = ?1")
    Answer findRelevantInfoAnswerByAnswerId(int answerId);

    // This method returns all answers belonging to a single question
    //      with ONLY the relevant information needed to display it on the front end
    @Query("SELECT new Answer (answerId, answer, totalUpvotes, totalDownvotes, creationDate, lastModifiedDate, " +
            "question.questionId, user.userId, user.firstName, user.lastName) from Answer where question = ?1")
    List<Answer> findRelevantInfoAnswersByQuestion(Question question);
}
