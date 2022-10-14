package com.app.domain.answer.service;

import com.app.domain.answer.entity.Answer;
import com.app.domain.answer.repository.AnswerRepository;
import com.app.domain.member.entity.Member;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public Answer getOneAnswerByQuestion(Long questionId) {
        return answerRepository.findByQuestion(questionId)
                .orElseGet(() -> Answer.builder()
                        .content("답변이 없습니다.")
                        .member(Member.notExistMember())
                        .build());
    }
}
