package com.mackenzie.cif.question.domain.repository;

import com.mackenzie.cif.question.domain.domain.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
    Question findByCode(String code);
}
