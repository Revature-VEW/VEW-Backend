package com.revature.vew.repositories;

import com.revature.vew.models.Question;
import com.revature.vew.models.QuestionComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionCommentRepository extends JpaRepository<QuestionComment, Integer> {
    // This method returns an answer comment with ONLY the relevant information needed to display it on the front end
    @Query("SELECT new QuestionComment(questionCommentId, comment, creationDate, lastModifiedDate, question.questionId, user.userId, " +
            "user.firstName, user.lastName) from QuestionComment where question = ?1")
    List<QuestionComment> findRelevantInfoQuestionsCommentByQuestion(Question question);
}
