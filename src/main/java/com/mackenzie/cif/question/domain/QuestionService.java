package com.mackenzie.cif.question.domain;

import com.mackenzie.cif.question.domain.dto.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class QuestionService {
    @Autowired
    private QuestionRepository rep;
    public List<QuestionDTO> getQuestions(){
        return rep.findAll().stream().map(QuestionDTO::create).collect(Collectors.toList());
    }

}
