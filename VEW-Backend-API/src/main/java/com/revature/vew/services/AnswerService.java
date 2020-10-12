package com.revature.vew.services;

import com.revature.vew.models.Answer;
import com.revature.vew.models.Question;
import com.revature.vew.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    private AnswerRepository answerRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Answer addAnswer(Answer newAnswer) {
        return answerRepository.save(newAnswer);
    }

    public Answer getAnswerByAnswerId(int answerId) {
        return answerRepository.findRelevantInfoAnswerByAnswerId(answerId);
    }

    public List<Answer> getAnswersByQuestion(Question question) {
        return answerRepository.findRelevantInfoAnswersByQuestion(question);
    }
}
