package com.app.api.answer.service;

import com.app.api.answer.dto.PatchAnswerRequestDto;
import com.app.api.answer.dto.PostAnswerRequestDto;
import com.app.domain.answer.entity.Answer;
import com.app.domain.answer.repository.AnswerRepository;
import com.app.domain.answer.service.AnswerService;
import com.app.domain.member.entity.Member;
import com.app.domain.member.service.MemberService;
import com.app.domain.question.constant.AnswerStatus;
import com.app.domain.question.entity.Question;
import com.app.domain.question.service.QuestionService;
import com.app.global.resolver.memberinfo.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerInfoService {

    private final AnswerService answerService;
    private final QuestionService questionService;
    private final MemberService memberService;


    @Transactional
    public Long postAnswer(MemberInfoDto memberInfoDto, PostAnswerRequestDto postAnswerRequestDto) {
        Member member = memberService.findMemberByMemberId(memberInfoDto.getMemberId());
        Question question = questionService.getOneQuestion(postAnswerRequestDto.getQuestionId());

        Answer answer = answerService.save(postAnswerRequestDto.toEntity(question, member));

        question.changeAnswerStatus(AnswerStatus.ANSWERED);

        return answer.getAnswerId();
    }

    @Transactional
    public Long modifyAnswer(PatchAnswerRequestDto patchAnswerRequestDto) {
        Answer answer = answerService.findById(patchAnswerRequestDto.getAnswerId());
        answer.changeContents(patchAnswerRequestDto.getContent());
        return answer.getAnswerId();
    }
}
