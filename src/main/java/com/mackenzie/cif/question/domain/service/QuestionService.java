package com.mackenzie.cif.question.domain.service;

import com.mackenzie.cif.question.domain.domain.Question;
import com.mackenzie.cif.question.domain.dto.QuestionsDTO;
import com.mackenzie.cif.question.domain.enums.GroupEnum;
import com.mackenzie.cif.question.domain.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mackenzie.cif.question.domain.enums.GroupEnum.*;

@Service
@Slf4j
public class QuestionService {
    @Autowired
    private QuestionRepository rep;

    public QuestionsDTO listAllQuestions(){
        log.info("Service list all questions >>>>>");
        List<Question> questions;
        try {
            questions = rep.findAll();
        }catch (Exception e){
            log.error("Error while getting questions in repository");
            throw e;
        }
        if(questions.isEmpty()) return null;

        List<Question> enviromentalFactors = sortList(filterByGroup(questions, ENVIRONMENTALFACTORS));
        List<Question> activityAndParticipation = sortList(filterByGroup(questions, ACTIVITYANDPARTICIPATION));
        List<Question> bodyStructures = sortList(filterByGroup(questions, BODYSTRUCTURES));
        List<Question> bodyFunctions = sortList(filterByGroup(questions, BODYFUNCTIONS));

        return QuestionsDTO
                .builder()
                .activityAndParticipation(activityAndParticipation)
                .environmentalFactors(enviromentalFactors)
                .bodyFunctions(bodyFunctions)
                .bodyStructures(bodyStructures)
                .build();
    }

    private List<Question> filterByGroup(List<Question> questions, GroupEnum groupEnum){
        return questions.stream().filter(question ->
             question
                 .getGroup()
                 .equals(groupEnum.label))
            .collect(Collectors.toList());
    }

    private List<Question> sortList(List<Question> questions){
        return questions.stream().sorted(Comparator.comparing(Question::getCode)).collect(Collectors.toList());
    }

    public Question findByCode(String code){
        log.info("Service find by code >>>>>");
        Question question = rep.findByCode(code);
        if(question == null) return null;
        return question;
    }

    public Question findById(String id){
        log.info("Service find by code >>>>>");
        Optional<Question> question = rep.findById(id);

        return question.orElse(null);
    }

    public void insert(Question question) {
        rep.save(question);
    }

    public Question update(Question question, String id){
        Optional<Question> optional = rep.findById(id);

        if(optional.isPresent()){
            Question db = optional.get();
            db.setCode(question.getCode());
            db.setDescription(question.getDescription());
            db.setTitle(question.getTitle());
            db.setGroup(question.getGroup());
            if(question.getExclusion() != null)
                db.setExclusion(question.getExclusion());
            if(question.getInclusion() != null)
                db.setInclusion(question.getInclusion());
            rep.save(db);
            return db;
        }else{
            throw new RuntimeException("Could not update registry");
        }
    }

    public boolean delete(String id){
        if(rep.findById(id).map(QuestionsDTO::create).isPresent()){
            rep.deleteById(id);
            return true;
        } return false;
    }
}
