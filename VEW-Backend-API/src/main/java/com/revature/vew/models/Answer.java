package com.revature.vew.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name= "answers")
public class Answer extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private int answer_id;
    @ManyToOne
    @JoinColumn(name="question_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Question question;
    private String answer;
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @Column(name = "total_upvotes", columnDefinition = "int default 0")
    private int totalUpvotes;
    @Column(name = "total_downvotes", columnDefinition = "int default 0")
    private int totalDownvotes;

    public Answer() { }

    public Answer(int answer_id) {
        this.answer_id = answer_id;
    }

    public Answer(User user, Question question, String answer) {
        this.user = user;
        this.question = question;
        this.answer = answer;
    }

    public Answer(int answer_id, String answer, int totalUpvotes, int totalDownvotes, Date creationDate,
                  Date lastModifiedDate, int questionId, int userId, String firstName, String lastName) {
        Question question = new Question(questionId);
        User user = new User(userId, firstName, lastName);
        this.answer_id = answer_id;
        this.answer = answer;
        this.totalUpvotes = totalUpvotes;
        this.totalDownvotes = totalDownvotes;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.user = user;
        this.question=question;
    }

    public int getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(int answer_id) {
        this.answer_id = answer_id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTotalUpvotes() {
        return totalUpvotes;
    }

    public void setTotalUpvotes(int totalUpvotes) {
        this.totalUpvotes = totalUpvotes;
    }

    public int getTotalDownvotes() {
        return totalDownvotes;
    }

    public void setTotalDownvotes(int totalDownvotes) {
        this.totalDownvotes = totalDownvotes;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answer_id=" + answer_id +
                ", question=" + question +
                ", answer='" + answer + '\'' +
                ", user=" + user +
                ", totalUpvotes=" + totalUpvotes +
                ", totalDownvotes=" + totalDownvotes +
                ", creationDate=" + creationDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
