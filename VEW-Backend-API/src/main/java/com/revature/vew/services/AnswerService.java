package com.revature.vew.services;

import com.revature.vew.models.Answer;
import com.revature.vew.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
