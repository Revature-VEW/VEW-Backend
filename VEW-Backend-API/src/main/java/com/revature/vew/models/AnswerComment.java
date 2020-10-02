package com.revature.vew.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "answer_comments")
public class AnswerComment extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "answer_comment_id")
    private int answerCommentId;
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @ManyToOne
    @JoinColumn(name="answer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Answer answer;
    private String comment;

    public AnswerComment() { }

    public AnswerComment(User user, Answer answer, String comment) {
        this.user = user;
        this.answer = answer;
        this.comment = comment;
    }

    public AnswerComment(int answerCommentId, String comment, Date creationDate,
                          Date lastModifiedDate, int answerId, int userId, String firstName, String lastName) {
        Answer answer = new Answer(answerId);
        User user = new User(userId, firstName, lastName);
        this.answerCommentId=answerCommentId;
        this.comment=comment;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.user = user;
        this.answer=answer;
    }

    public int getAnswerCommentId() {
        return answerCommentId;
    }

    public void setAnswerCommentId(int answerCommentId) {
        this.answerCommentId = answerCommentId;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "AnswerComment{" +
                "answerCommentId=" + answerCommentId +
                ", user=" + user +
                ", answer=" + answer +
                ", comment='" + comment + '\'' +
                ", creationDate=" + creationDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
