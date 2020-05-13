package com.mackenzie.cif.question.api;

import com.mackenzie.cif.question.domain.domain.Question;
import com.mackenzie.cif.question.domain.dto.QuestionsDTO;
import com.mackenzie.cif.question.domain.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@Slf4j
public class QuestionController {

    @Autowired
    private QuestionService service;

    @CrossOrigin("*")
    @GetMapping("/questions")
    public ResponseEntity<?> listAll(){
        log.info("List all questions >>>>>");
        QuestionsDTO questions;
        try{
            questions = service.listAllQuestions();
        }catch (Exception e){
            log.error("Error while listing all questions",
                    e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }
    
    @CrossOrigin("*")
    @GetMapping("questionCode/{code}")
    public ResponseEntity<?> findByCode(@PathVariable("code") String code){
        Question response;
        try{
             response = service.findByCode(code);
        }catch (Exception e){
            log.error("Error while finding question with code "+code, e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(response == null){
            return new ResponseEntity<>("Question with code "+code+" not found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @CrossOrigin("*")
    @GetMapping("questionId/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id){
        Question response;
        try{
            response = service.findById(id);
        }catch (Exception e){
            log.error("Error while finding question with code "+id, e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(response == null){
            return new ResponseEntity<>("Question with code "+id+" not found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @CrossOrigin("*")
    @PostMapping("question/save")
    public ResponseEntity<?> insert(@RequestBody Question question){
        try{
            service.insert(question);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Could not insert question",HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @CrossOrigin("*")
    @PutMapping("question/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Question question){
        question.setId(id);
        Question response = service.update(question,id);

        return response != null?
                ResponseEntity.ok(response):
                ResponseEntity.notFound().build();
    }

    @CrossOrigin("*")
    @DeleteMapping("question/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        boolean ok = service.delete(id);

        return ok ?
                ResponseEntity.ok().build():
                ResponseEntity.notFound().build();
    }
}
