package com.mackenzie.cif.question.domain;

import com.mackenzie.cif.question.domain.dto.QuestionDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Slf4j
public class QuestionService {
    @Autowired
    private QuestionRepository rep;
    public List<QuestionDTO> listAllQuestions(){
        log.info("Service list all questions >>>>>");
        return rep.findAll().stream().map(QuestionDTO::create).collect(Collectors.toList());
    }

    public QuestionDTO findByCode(String code){
        log.info("Service find by code >>>>>");
        Question question = rep.findByCode(code);
        if(question == null){
            return null;
        }
        QuestionDTO questionDTO = QuestionDTO.create(question);
        return questionDTO;
    }

    public QuestionDTO findById(Integer id){
        log.info("Service find by code >>>>>");
        Optional<Question> question = rep.findById(id);
        if(question == null){
            return null;
        }
        QuestionDTO questionDTO = QuestionDTO.create(question.get());
        return questionDTO;
    }

}
