package com.mackenzie.cif.question.api;

import com.mackenzie.cif.question.domain.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/questions")
public class CarrosController {
    @Autowired
    private QuestionService service;

    @GetMapping("/questions")
    public ResponseEntity get(){
        return new ResponseEntity<>(service.getQuestions(), HttpStatus.OK);
    }

}
