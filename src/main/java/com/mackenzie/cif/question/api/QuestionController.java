package com.mackenzie.cif.question.api;

import com.mackenzie.cif.question.domain.domain.Question;
import com.mackenzie.cif.question.domain.dto.QuestionDTO;
import com.mackenzie.cif.question.domain.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("questionCode/{code}")
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

    @GetMapping("questionId/{id}")
    public ResponseEntity findById(@PathVariable("id") String id){
        QuestionDTO response = null;
        try{
            response = service.findById(id);
        }catch (Exception e){
            log.error("Error while finding question with code "+id, e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(response == null){
            return new ResponseEntity("Question with code "+id+" not found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @PostMapping("question/save")
    public ResponseEntity insert(@RequestBody Question question){
        try{
            QuestionDTO response = service.insert(question);
            URI location = getUri(response.getId());
            return ResponseEntity.created(location).build();
        }catch (Exception e){
            return new ResponseEntity("Could not insert question",HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping("question/{id}")
    public ResponseEntity update(@PathVariable String id, @RequestBody Question question){
        question.setId(id);
        QuestionDTO response = service.update(question,id);

        return response != null?
                ResponseEntity.ok(response):
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("question/{id}")
    public ResponseEntity delete(@PathVariable("id") String id){
        boolean ok = service.delete(id);

        return ok ?
                ResponseEntity.ok().build():
                ResponseEntity.notFound().build();
    }

    private URI getUri(String id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("id")
                .buildAndExpand(id).toUri();
    }
}
