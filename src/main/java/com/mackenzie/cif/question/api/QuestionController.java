package com.mackenzie.cif.question.api;

import com.mackenzie.cif.question.domain.QuestionService;
import com.mackenzie.cif.question.domain.dto.QuestionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
@Slf4j
public class QuestionController {

    @Autowired
    private QuestionService service;

    @GetMapping("/listAll")
    public ResponseEntity listAll(){
        log.info("List all questions >>>>>");
        List<QuestionDTO> questions = new ArrayList();
        try{
            questions = service.listAllQuestions();
        }catch (Exception e){
            log.error("Error while listing all questions", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("question/{code}")
    public ResponseEntity findByCode(@PathVariable("code") String code){
        QuestionDTO response = null;
        try{
             response = service.findByCode(code);
        }catch (Exception e){
            log.error("Error while finding question with code "+code, e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(response == null){
            return new ResponseEntity("Question with code "+code+" not found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(response,HttpStatus.OK);
    }
}
