package com.revature.vew.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AnswerRankingTest {
    @Test
    public void testSetAnswerRankingId() {
        AnswerRanking answerRanking = new AnswerRanking();
        answerRanking.setAnswerRankingId(123);
        assertEquals(123, answerRanking.getAnswerRankingId());
    }

    @Test
    public void testSetUser() {
        AnswerRanking answerRanking = new AnswerRanking();
        answerRanking.setUser(new User());
        assertEquals(
                "AnswerRanking{answerRankingId=0, user=User{userId=0, username='null', password='null', firstName='null',"
                        + " lastName='null', role=null}, answer=null, upvote=false, creationDate=null, lastModifiedDate=null" + "}",
                answerRanking.toString());
    }

    @Test
    public void testSetAnswer() {
        AnswerRanking answerRanking = new AnswerRanking();
        answerRanking.setAnswer(new Answer());
        assertEquals("AnswerRanking{answerRankingId=0, user=null, answer=Answer{answer_id=0, question=null, answer='null',"
                + " user=null, totalUpvotes=0, totalDownvotes=0, creationDate=null, lastModifiedDate=null}, upvote=false,"
                + " creationDate=null, lastModifiedDate=null}", answerRanking.toString());
    }

    @Test
    public void testSetUpvote() {
        AnswerRanking answerRanking = new AnswerRanking();
        answerRanking.setUpvote(true);
        assertTrue(answerRanking.isUpvote());
    }

    @Test
    public void testToString() {
        assertEquals(
                "AnswerRanking{answerRankingId=0, user=null, answer=null, upvote=false, creationDate=null, lastModifiedDate"
                        + "=null}",
                (new AnswerRanking()).toString());
    }
}

