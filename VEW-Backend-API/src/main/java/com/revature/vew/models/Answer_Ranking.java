package com.revature.vew.models;

import javax.persistence.*;

@Entity
@Table(name = "answer_ranking")
public class Answer_Ranking extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_ranking_id")
    private int answerRankingId;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "answer_id", nullable = false)
    private Answer answer;
    private boolean upvote;

    public Answer_Ranking(){

    }

    public Answer_Ranking(User user, Answer answer,boolean upvote){
        this.user = user;
        this.answer = answer;
        this.upvote = upvote;
    }

    public int getAnswerRankingId() {
        return answerRankingId;
    }

    public void setAnswerRankingId(int answerRankingId) {
        this.answerRankingId = answerRankingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public boolean isUpvote() {
        return upvote;
    }

    public void setUpvote(boolean upvote) {
        this.upvote = upvote;
    }

    @Override
    public String toString() {
        return "Answer_Ranking{" +
                "answerRankingId=" + answerRankingId +
                ", user=" + user +
                ", answer=" + answer +
                ", upvote=" + upvote +
                ", createdBy=" + createdBy +
                ", creationDate=" + creationDate +
                ", lastModifiedBy=" + lastModifiedBy +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
