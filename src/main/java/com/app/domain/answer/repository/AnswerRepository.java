package com.app.domain.answer.repository;

import com.app.domain.answer.entity.Answer;
import com.app.domain.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query("select a " +
            "from Answer a " +
            "where a.question.questionId = :questionId " +
            "and a.generalStatus = 'ACTIVE' ")
    public Optional<Answer> findByQuestion(Long questionId);


    @Query("select (count(a) > 0) " +
            "from Answer a where a.answerId = ?1")
    boolean existsByAnswerId(Long answerId);


    @Query("select (count(a) > 0) " +
            "from Answer a " +
            "where a.question.questionId = :questionId " +
            "and a.generalStatus = 'ACTIVE' ")
    boolean hasAnswer(Long questionId);
}
