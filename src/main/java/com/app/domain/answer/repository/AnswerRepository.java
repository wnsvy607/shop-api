package com.app.domain.answer.repository;

import com.app.domain.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query("select a " +
            "from Answer a " +
            "where a.question.questionId = :questionId " +
            "and a.generalStatus = 'ACTIVE' ")
    public Optional<Answer> findByQuestion(Long questionId);


}
