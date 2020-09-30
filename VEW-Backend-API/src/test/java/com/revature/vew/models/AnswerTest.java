package com.revature.vew.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AnswerTest {
    @Test
    public void testSetAnswer_id() {
        Answer answer = new Answer();
        answer.setAnswer_id(1);
        assertEquals(1, answer.getAnswer_id());
    }

    @Test
    public void testSetQuestion() {
        Answer answer = new Answer();
        answer.setQuestion(new Question());
        assertEquals(
                "Answer{answer_id=0, question=Question{questionId=0, user=null, question='null', approved=false,"
                        + " totalUpvotes=0, totalDownvotes=0, tags=[], creationDate=null, lastModifiedDate=null}, answer='null',"
                        + " user=null, totalUpvotes=0, totalDownvotes=0, creationDate=null, lastModifiedDate=null}",
                answer.toString());
    }

    @Test
    public void testSetAnswer() {
        Answer answer = new Answer();
        answer.setAnswer("Answer");
        assertEquals("Answer", answer.getAnswer());
    }

    @Test
    public void testSetUser() {
        Answer answer = new Answer();
        answer.setUser(new User());
        assertEquals(
                "Answer{answer_id=0, question=null, answer='null', user=User{userId=0, username='null', password='null',"
                        + " firstName='null', lastName='null', role=null}, totalUpvotes=0, totalDownvotes=0, creationDate=null,"
                        + " lastModifiedDate=null}",
                answer.toString());
    }

    @Test
    public void testSetTotalUpvotes() {
        Answer answer = new Answer();
        answer.setTotalUpvotes(1);
        assertEquals(1, answer.getTotalUpvotes());
    }

    @Test
    public void testSetTotalDownvotes() {
        Answer answer = new Answer();
        answer.setTotalDownvotes(1);
        assertEquals(1, answer.getTotalDownvotes());
    }

    @Test
    public void testToString() {
        assertEquals("Answer{answer_id=0, question=null, answer='null', user=null, totalUpvotes=0, totalDownvotes=0,"
                + " creationDate=null, lastModifiedDate=null}", (new Answer()).toString());
    }
}

