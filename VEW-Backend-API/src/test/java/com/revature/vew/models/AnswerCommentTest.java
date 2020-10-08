package com.revature.vew.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class AnswerCommentTest {
    @Test
    public void testConstructor() {
        Date creationDate = new Date(1L);
        AnswerComment actualAnswerComment = new AnswerComment(123, "Comment", creationDate, new Date(1L), 123, 123, "Jane",
                "Doe");
        int actualAnswer_id = actualAnswerComment.getAnswer().getAnswerId();
        assertEquals("Comment", actualAnswerComment.getComment());
        User user = actualAnswerComment.getUser();
        assertEquals(123, actualAnswerComment.getAnswerCommentId());
        assertEquals(
                "AnswerComment{answerCommentId=123, user=User{userId=123, username='null', password='null', firstName='Jane',"
                        + " lastName='Doe', role=null}, answer=Answer{answerId=123, question=null, answer='null', user=null,"
                        + " totalUpvotes=0, totalDownvotes=0, creationDate=null, lastModifiedDate=null}, comment='Comment',"
                        + " creationDate=Wed Dec 31 19:00:00 EST 1969, lastModifiedDate=Wed Dec 31 19:00:00 EST 1969}",
                actualAnswerComment.toString());
        assertEquals("Doe", user.getLastName());
        assertEquals(123, actualAnswer_id);
        assertEquals("Jane", user.getFirstName());
        assertEquals(123, user.getUserId());
    }

    @Test
    public void testSetAnswerCommentId() {
        AnswerComment answerComment = new AnswerComment();
        answerComment.setAnswerCommentId(123);
        assertEquals(123, answerComment.getAnswerCommentId());
    }

    @Test
    public void testSetUser() {
        AnswerComment answerComment = new AnswerComment();
        answerComment.setUser(new User());
        assertEquals(
                "AnswerComment{answerCommentId=0, user=User{userId=0, username='null', password='null', firstName='null',"
                        + " lastName='null', role=null}, answer=null, comment='null', creationDate=null, lastModifiedDate=null"
                        + "}",
                answerComment.toString());
    }

    @Test
    public void testSetAnswer() {
        AnswerComment answerComment = new AnswerComment();
        answerComment.setAnswer(new Answer());
        assertEquals("AnswerComment{answerCommentId=0, user=null, answer=Answer{answerId=0, question=null, answer='null',"
                + " user=null, totalUpvotes=0, totalDownvotes=0, creationDate=null, lastModifiedDate=null}, comment='null',"
                + " creationDate=null, lastModifiedDate=null}", answerComment.toString());
    }

    @Test
    public void testSetComment() {
        AnswerComment answerComment = new AnswerComment();
        answerComment.setComment("Comment");
        assertEquals("Comment", answerComment.getComment());
    }

    @Test
    public void testToString() {
        assertEquals("AnswerComment{answerCommentId=0, user=null, answer=null, comment='null', creationDate=null,"
                + " lastModifiedDate=null}", (new AnswerComment()).toString());
    }
}

