package com.revature.vew.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "question_comment")
public class QuestionComment extends Auditable<String>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_comment_id")
	private int questionCommentId;
	@ManyToOne
	@JoinColumn(name = "question_id", nullable = false)
	private Question question;
	@Column(name = "comment", nullable = false)
	private String comment;

	public QuestionComment() {
	}

	public QuestionComment(Question question, String comment) {
		this.question = question;
		this.comment = comment;
	}

	public int getQuestionCommentId() {
		return questionCommentId;
	}

	public void setQuestionCommentId(int questionCommentId) {
		this.questionCommentId = questionCommentId;
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
	public String toString(){
		return "QuestionComment{" +
				", questionCommentID=" + questionCommentId +
				", question=" + question +
				", comment=" + comment +
				", creationDate=" + creationDate +
				'}';
	}
}
