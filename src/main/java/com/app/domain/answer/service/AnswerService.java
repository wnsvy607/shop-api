package com.app.domain.answer.service;

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
    private final QuestionService questionService;
    private final MemberService memberService;

    public Answer getOneAnswerByQuestion(Long questionId) {
        return answerRepository.findByQuestion(questionId)
                .orElseGet(() -> Answer.builder()
                        .content("답변이 없습니다.")
                        .member(Member.notExistMember())
                        .build());
    }

    @Transactional
    public Long postAnswer(MemberInfoDto memberInfoDto, PostAnswerRequestDto postAnswerRequestDto) {
        Member member = memberService.findMemberByMemberId(memberInfoDto.getMemberId());
        Question question = questionService.getOneQuestion(postAnswerRequestDto.getQuestionId());

        Answer answer = answerRepository.save(postAnswerRequestDto.toEntity(question, member));

        question.changeAnswerStatus(AnswerStatus.ANSWERED);

        return answer.getAnswerId();
    }
}
