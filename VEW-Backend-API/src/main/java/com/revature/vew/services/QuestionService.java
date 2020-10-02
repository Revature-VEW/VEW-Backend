package com.revature.vew.services;

import com.revature.vew.models.Question;
import com.revature.vew.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    private QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    public Question addQuestion(Question newQuestion){
        return questionRepository.save(newQuestion);
    }

    public Question getQuestionByQuestionId(int id) {
        return questionRepository.findRelevantInfoQuestionByQuestionId(id);
    }
}
