package com.app.domain.question.service;

import com.app.api.question.dto.QuestionListResponseDto;
import com.app.domain.question.entity.Question;
import com.app.domain.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    @Transactional(readOnly = true)
    public List<Question> getQuestionList(Pageable pageable) {
        return questionRepository.findAllOrderByQuestionId(pageable).getContent();
    }
}
