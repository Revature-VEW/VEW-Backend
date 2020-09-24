package com.revature.vew.repositories;

import com.revature.vew.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    @Query("SELECT new Answer (answer_id, answer, totalUpvotes, totalDownvotes, creationDate, lastModifiedDate, question.questionId,user.userId, " +
            "user.firstName, user.lastName) from Answer where answer_id = ?1")
    Answer findRelevantInformationAnswerByAnswerId(int id);
}
