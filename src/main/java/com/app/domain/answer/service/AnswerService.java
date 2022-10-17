package com.app.domain.answer.service;

import com.app.api.answer.dto.PatchAnswerRequestDto;
import com.app.api.answer.dto.PostAnswerRequestDto;
import com.app.domain.answer.entity.Answer;
import com.app.domain.answer.repository.AnswerRepository;
import com.app.domain.member.entity.Member;
import com.app.domain.member.service.MemberService;
import com.app.domain.question.constant.AnswerStatus;
import com.app.domain.question.entity.Question;
import com.app.domain.question.service.QuestionService;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.EntityNotFoundException;
import com.app.global.resolver.memberinfo.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public Answer findByQuestion(Long questionId) {
        return answerRepository.findByQuestion(questionId)
                .orElseGet(() -> Answer.builder()
                        .content("답변이 없습니다.")
                        .member(Member.notExistMember())
                        .build());
    }

    public Answer save(Answer answer) {
        return answerRepository.save(answer);
    }

    public Answer findById(Long answerId) {
        return answerRepository.findById(answerId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ANSWER_NOT_EXISTS));
    }
}
