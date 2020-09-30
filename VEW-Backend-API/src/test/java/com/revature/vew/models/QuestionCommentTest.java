package com.revature.vew.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class QuestionCommentTest {
    @Test
    public void testConstructor() {
        Date creationDate = new Date(1L);
        QuestionComment actualQuestionComment = new QuestionComment(123, "Comment", creationDate, new Date(1L), 123, 123,
                "Jane", "Doe");
        Question question = actualQuestionComment.getQuestion();
        String actualToStringResult = question.toString();
        assertEquals("Comment", actualQuestionComment.getComment());
        User user = actualQuestionComment.getUser();
        assertEquals("QuestionComment{questionCommentId=123, user=User{userId=123, username='null', password='null',"
                + " firstName='Jane', lastName='Doe', role=null}, question=Question{questionId=123, user=null, question='null',"
                + " approved=false, totalUpvotes=0, totalDownvotes=0, tags=[], creationDate=null, lastModifiedDate=null},"
                + " comment='Comment', creationDate=Wed Dec 31 19:00:00 EST 1969, lastModifiedDate=Wed Dec 31 19:00:00"
                + " EST 1969}", actualQuestionComment.toString());
        assertEquals(123, actualQuestionComment.getQuestionCommentId());
        assertEquals("Doe", user.getLastName());
        assertEquals(
                "Question{questionId=123, user=null, question='null', approved=false, totalUpvotes=0, totalDownvotes=0,"
                        + " tags=[], creationDate=null, lastModifiedDate=null}",
                actualToStringResult);
        assertEquals(123, question.getQuestionId());
        assertEquals("Jane", user.getFirstName());
        assertEquals(123, user.getUserId());
    }

    @Test
    public void testSetQuestionCommentId() {
        QuestionComment questionComment = new QuestionComment();
        questionComment.setQuestionCommentId(123);
        assertEquals(123, questionComment.getQuestionCommentId());
    }

    @Test
    public void testSetUser() {
        QuestionComment questionComment = new QuestionComment();
        questionComment.setUser(new User());
        assertEquals(
                "QuestionComment{questionCommentId=0, user=User{userId=0, username='null', password='null', firstName='null',"
                        + " lastName='null', role=null}, question=null, comment='null', creationDate=null, lastModifiedDate=null"
                        + "}",
                questionComment.toString());
    }

    @Test
    public void testSetQuestion() {
        QuestionComment questionComment = new QuestionComment();
        questionComment.setQuestion(new Question());
        assertEquals(
                "QuestionComment{questionCommentId=0, user=null, question=Question{questionId=0, user=null, question='null',"
                        + " approved=false, totalUpvotes=0, totalDownvotes=0, tags=[], creationDate=null, lastModifiedDate=null},"
                        + " comment='null', creationDate=null, lastModifiedDate=null}",
                questionComment.toString());
    }

    @Test
    public void testSetComment() {
        QuestionComment questionComment = new QuestionComment();
        questionComment.setComment("Comment");
        assertEquals("Comment", questionComment.getComment());
    }

    @Test
    public void testToString() {
        assertEquals("QuestionComment{questionCommentId=0, user=null, question=null, comment='null', creationDate=null,"
                + " lastModifiedDate=null}", (new QuestionComment()).toString());
    }
}

