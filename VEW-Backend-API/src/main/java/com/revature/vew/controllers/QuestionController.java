package com.revature.vew.controllers;

import com.revature.vew.models.Question;
import com.revature.vew.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@CrossOrigin
@RequestMapping(path = "/question")
public class QuestionController {

    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService){
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<?> addQuestion(@RequestBody Question newQuestion) throws URISyntaxException {
        Question addedQuestion = this.questionService.addQuestion(newQuestion);

        return new ResponseEntity<>(addedQuestion, HttpStatus.CREATED);
    }

    @GetMapping(path="{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getQuestionByQuestionId(@PathVariable int id) {
        Question questionFoundById = questionService.getQuestionbyQuestionId(id);
        return new ResponseEntity<>(questionFoundById, HttpStatus.OK);
    }
}
