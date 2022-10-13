package com.app.domain.question.repository;

import com.app.domain.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("select q " +
            "from Question q " +
            "where q.generalStatus = 'ACTIVE'  " +
            "order by q.questionId DESC")
    // pageable 은 join fetch 처리가 불가능함(메모리에 적재되어 join fetch 됨)
    public Page<Question> findAllOrderByQuestionId(Pageable pageable);
}
