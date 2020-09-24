package com.revature.vew.repositories;

import com.revature.vew.models.AnswerComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnswerCommentRepository  extends JpaRepository<AnswerComment, Integer> {
    // This method returns an answer comment with ONLY the relevant information needed to display it on the front end
    @Query("SELECT new AnswerComment (answerCommentId, comment, creationDate, lastModifiedDate, answer.answer_id,user.userId, " +
            "user.firstName, user.lastName) from AnswerComment where answerCommentId = ?1")
    AnswerComment findRelevantInformationAnswerCommentByAnswerCommentId(int id);
}