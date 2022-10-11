package com.app.domain.question.service;

import com.app.domain.question.entity.Question;
import com.app.domain.question.repository.QuestionRepository;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
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

    @Transactional(readOnly = true)
    public Question getOneQuestion(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.QUESTION_NOT_EXISTS));
    }

    @Transactional(readOnly = true)
    public boolean isAuthor(Long memberId,Question question){
        return memberId.equals(question.getMember().getMemberId());
    }


    public Long deleteQuestion(Question question) {
        question.delete();
        return question.getQuestionId();
    }
}
