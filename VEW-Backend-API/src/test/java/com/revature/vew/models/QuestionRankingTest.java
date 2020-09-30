package com.revature.vew.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class QuestionRankingTest {
    @Test
    public void testSetUserQuestionRankingId() {
        QuestionRanking questionRanking = new QuestionRanking();
        questionRanking.setUserQuestionRankingId(123);
        assertEquals(123, questionRanking.getQuestionRankingId());
    }

    @Test
    public void testSetUser() {
        QuestionRanking questionRanking = new QuestionRanking();
        questionRanking.setUser(new User());
        assertEquals("UserQuestionRanking{userQuestionRankingId=0, user=User{userId=0, username='null', password='null',"
                + " firstName='null', lastName='null', role=null}, question=null, upvote=false, createdBy=null,"
                + " creationDate=null, lastModifiedBy=null, lastModifiedDate=null}", questionRanking.toString());
    }

    @Test
    public void testSetQuestion() {
        QuestionRanking questionRanking = new QuestionRanking();
        questionRanking.setQuestion(new Question());
        assertEquals("UserQuestionRanking{userQuestionRankingId=0, user=null, question=Question{questionId=0, user=null,"
                + " question='null', approved=false, totalUpvotes=0, totalDownvotes=0, tags=[], creationDate=null,"
                + " lastModifiedDate=null}, upvote=false, createdBy=null, creationDate=null, lastModifiedBy=null,"
                + " lastModifiedDate=null}", questionRanking.toString());
    }

    @Test
    public void testSetUpvote() {
        QuestionRanking questionRanking = new QuestionRanking();
        questionRanking.setUpvote(true);
        assertTrue(questionRanking.isUpvote());
    }

    @Test
    public void testToString() {
        assertEquals("UserQuestionRanking{userQuestionRankingId=0, user=null, question=null, upvote=false, createdBy=null,"
                + " creationDate=null, lastModifiedBy=null, lastModifiedDate=null}", (new QuestionRanking()).toString());
    }
}

