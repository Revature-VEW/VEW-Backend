package com.revature.vew.models;

import javax.persistence.*;

@Entity
@Table(name = "question_ranking")
public class QuestionRanking extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_ranking_id")
    private int questionRankingId;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
    private boolean upvote;

    public QuestionRanking() {
    }

    public QuestionRanking(User user, Question question, boolean upvote) {
        this.user = user;
        this.question = question;
        this.upvote = upvote;
    }

    public int getQuestionRankingId() {
        return questionRankingId;
    }

    public void setUserQuestionRankingId(int userQuestionRankingId) {
        this.questionRankingId = userQuestionRankingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean isUpvote() {
        return upvote;
    }

    public void setUpvote(boolean upvote) {
        this.upvote = upvote;
    }

    @Override
    public String toString() {
        return "UserQuestionRanking{" +
                "userQuestionRankingId=" + questionRankingId +
                ", user=" + user +
                ", question=" + question +
                ", upvote=" + upvote +
                ", createdBy=" + createdBy +
                ", creationDate=" + creationDate +
                ", lastModifiedBy=" + lastModifiedBy +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
