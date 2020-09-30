package com.revature.vew.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class QuestionTest {
    @Test
    public void testConstructor() {
        Question actualQuestion = new Question(new User(), "Question");
        assertEquals("Question", actualQuestion.getQuestion());
        assertEquals("Question{questionId=0, user=User{userId=0, username='null', password='null', firstName='null',"
                + " lastName='null', role=null}, question='Question', approved=false, totalUpvotes=0, totalDownvotes=0,"
                + " tags=[], creationDate=null, lastModifiedDate=null}", actualQuestion.toString());
    }

    @Test
    public void testConstructor2() {
        Question actualQuestion = new Question(123);
        assertEquals(
                "Question{questionId=123, user=null, question='null', approved=false, totalUpvotes=0, totalDownvotes=0,"
                        + " tags=[], creationDate=null, lastModifiedDate=null}",
                actualQuestion.toString());
        assertEquals(123, actualQuestion.getQuestionId());
    }

    @Test
    public void testConstructor3() {
        assertEquals("Question{questionId=0, user=null, question='null', approved=false, totalUpvotes=0, totalDownvotes=0,"
                + " tags=[], creationDate=null, lastModifiedDate=null}", (new Question()).toString());
    }

    @Test
    public void testSetQuestionId() {
        Question question = new Question();
        question.setQuestionId(123);
        assertEquals(123, question.getQuestionId());
    }

    @Test
    public void testSetUser() {
        Question question = new Question();
        question.setUser(new User());
        assertEquals("Question{questionId=0, user=User{userId=0, username='null', password='null', firstName='null',"
                + " lastName='null', role=null}, question='null', approved=false, totalUpvotes=0, totalDownvotes=0,"
                + " tags=[], creationDate=null, lastModifiedDate=null}", question.toString());
    }

    @Test
    public void testSetQuestion() {
        Question question = new Question();
        question.setQuestion("Question");
        assertEquals("Question", question.getQuestion());
    }

    @Test
    public void testSetApproved() {
        Question question = new Question();
        question.setApproved(true);
        assertTrue(question.isApproved());
    }

    @Test
    public void testSetTotalUpvotes() {
        Question question = new Question();
        question.setTotalUpvotes(1);
        assertEquals(1, question.getTotalUpvotes());
    }

    @Test
    public void testSetTotalDownvotes() {
        Question question = new Question();
        question.setTotalDownvotes(1);
        assertEquals(1, question.getTotalDownvotes());
    }

    @Test
    public void testSetTags() {
        Question question = new Question();
        question.setTags(new HashSet<Tag>());
        assertEquals("Question{questionId=0, user=null, question='null', approved=false, totalUpvotes=0, totalDownvotes=0,"
                + " tags=[], creationDate=null, lastModifiedDate=null}", question.toString());
    }

    @Test
    public void testToString() {
        assertEquals("Question{questionId=0, user=null, question='null', approved=false, totalUpvotes=0, totalDownvotes=0,"
                + " tags=[], creationDate=null, lastModifiedDate=null}", (new Question()).toString());
    }
}

