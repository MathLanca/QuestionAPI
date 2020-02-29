package com.mackenzie.cif.question.domain.repository;

import com.mackenzie.cif.question.domain.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findByCode(String code);
}
