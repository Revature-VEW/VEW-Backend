package com.revature.vew.repositories;

import com.revature.vew.models.Answer_Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnswerCommentRepository  extends JpaRepository<Answer_Comment, Integer> {

    @Query("SELECT new Answer_Comment (answerCommentId, comment, creationDate, lastModifiedDate, answer.answer_id,user.userId, " +
            "user.firstName, user.lastName) from Answer_Comment where answerCommentId = ?1")
    Answer_Comment findRelevantInformationCommentByCommentId(int id);
}
