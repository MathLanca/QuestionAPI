package com.mackenzie.cif.question.domain.dto;

import com.mackenzie.cif.question.domain.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionsDTO {
    private List<Question> environmentalFactors;
    private List<Question> activityAndParticipation;
    private List<Question> bodyStructures;
    private List<Question> bodyFunctions;

    public static QuestionsDTO create(Question q){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(q, QuestionsDTO.class);
    }
}
