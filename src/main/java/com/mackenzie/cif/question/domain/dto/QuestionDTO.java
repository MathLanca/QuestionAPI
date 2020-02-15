package com.mackenzie.cif.question.domain.dto;

import com.mackenzie.cif.question.domain.Question;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class QuestionDTO {
    private Integer id;
    private String code;
    private String tipo;

    public static QuestionDTO create(Question q){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(q, QuestionDTO.class);
    }
}
