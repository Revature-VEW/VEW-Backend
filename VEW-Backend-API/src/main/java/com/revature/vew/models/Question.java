package com.revature.vew.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "questions")
public class Question extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private int questionId;
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    private String question;
    @Column(name = "total_upvotes", columnDefinition = "int default 0")
    private int totalUpvotes;
    @Column(name = "total_downvotes", columnDefinition = "int default 0")
    private int totalDownvotes;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "questions_tags",
            joinColumns = {
                @JoinColumn(name = "question_id", referencedColumnName = "question_id", nullable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "tag_id", referencedColumnName = "tag_id", nullable = false)})
    private Set<Tag> tags = new HashSet<>();

    public Question() { }

    public Question(User user, String question) {
        this.user = user;
        this.question = question;
    }

    public Question(int questionId){
        this.questionId = questionId;
    }
    public Question(int questionId, String question, int totalUpvotes, int totalDownvotes, Date creationDate,
                    Date lastModifiedDate, int userId, String firstName, String lastName) {
        User user = new User(userId, firstName, lastName);
        this.questionId = questionId;
        this.question = question;
        this.totalUpvotes = totalUpvotes;
        this.totalDownvotes = totalDownvotes;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.user = user;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", user=" + user +
                ", question='" + question + '\'' +
                ", totalUpvotes=" + totalUpvotes +
                ", totalDownvotes=" + totalDownvotes +
                ", tags=" + tags +
                ", creationDate=" + creationDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
