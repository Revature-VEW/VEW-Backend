package com.revature.vew.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "question_comments")
public class QuestionComment extends Auditable<String> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_comment_id")
	private int questionCommentId;
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	@ManyToOne
	@JoinColumn(name = "question_id", nullable = false)
	private Question question;
	@Column(name = "comment", nullable = false)
	private String comment;

	public QuestionComment() { }

	public QuestionComment(User user, Question question, String comment) {
		this.user = user;
		this.question = question;
		this.comment = comment;
	}

	public QuestionComment(int questionCommentId, String comment, Date creationDate, Date lastModifiedDate, int questionId,
						   int userId, String firstName, String lastName) {
		User user = new User(userId, firstName, lastName);
		Question question = new Question(questionId);
		this.questionCommentId = questionCommentId;
		this.comment = comment;
		this.creationDate = creationDate;
		this. lastModifiedDate = lastModifiedDate;
		this.user = user;
		this.question = question;
	}

	public int getQuestionCommentId() {
		return questionCommentId;
	}

	public void setQuestionCommentId(int questionCommentId) {
		this.questionCommentId = questionCommentId;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "QuestionComment{" +
				"questionCommentId=" + questionCommentId +
				", user=" + user +
				", question=" + question +
				", comment='" + comment + '\'' +
				", creationDate=" + creationDate +
				", lastModifiedDate=" + lastModifiedDate +
				'}';
	}
}
