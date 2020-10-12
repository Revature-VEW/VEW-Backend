package com.revature.vew.controllers;

import com.revature.vew.models.Answer;
import com.revature.vew.models.Question;
import com.revature.vew.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/answer")
public class AnswerController {
    private AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService){
        this.answerService = answerService;
    }

    @PostMapping
    public ResponseEntity<?> addAnswer(@RequestBody Answer newAnswer) throws URISyntaxException {
        Answer addedAnswer = this.answerService.addAnswer(newAnswer);
        return new ResponseEntity<>(addedAnswer, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAnswerByAnswerId(@PathVariable int id) throws URISyntaxException {
        Answer answerFoundById = answerService.getAnswerByAnswerId(id);
        return new ResponseEntity<>(answerFoundById, HttpStatus.OK);
    }

    @GetMapping(path = "/question/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAnswersByQuestion(@PathVariable int id) throws URISyntaxException {
        Question inputQuestion = new Question(id);
        List<Answer> answersFoundByQuestion = answerService.getAnswersByQuestion(inputQuestion);
        return new ResponseEntity<>(answersFoundByQuestion, HttpStatus.OK);
    }
}
