package com.app.api.question.service;

import com.app.api.answer.service.AnswerInfoService;
import com.app.api.question.dto.*;
import com.app.domain.answer.entity.Answer;
import com.app.domain.answer.service.AnswerService;
import com.app.domain.member.constant.Role;
import com.app.domain.member.entity.Member;
import com.app.domain.member.service.MemberService;
import com.app.domain.question.constant.AccessLevel;
import com.app.domain.question.entity.Question;
import com.app.domain.question.repository.QuestionRepository;
import com.app.domain.question.service.QuestionService;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.AuthenticationException;
import com.app.global.resolver.memberinfo.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class QuestionInfoService {

    private final QuestionService questionService;
    private final MemberService memberService;
    private final AnswerService answerService;


    public Long postQuestion(MemberInfoDto memberInfoDto,
                             PostQuestionRequestDto postQuestionRequestDto) {
        Member member = memberService.findValidMember(memberInfoDto.getMemberId());
        Question question = postQuestionRequestDto.toEntity(member);
        return questionService.save(question).getQuestionId();
    }

    public GetQuestionPageInfoDto getQuestionListDto(Pageable pageable) {
        Page<Question> page = questionService.getQuestionList(pageable);
        List<GetQuestionListResponseDto> dtoList = page.getContent()
                .stream().map(GetQuestionListResponseDto::from)
                .collect(Collectors.toList());
        return GetQuestionPageInfoDto.of(dtoList, page.getTotalPages());
    }

    public Long modifyQuestion(MemberInfoDto memberInfoDto, PatchQuestionRequestDto patchQuestionRequestDto) {
        Question question = questionService.getOneQuestion(patchQuestionRequestDto.getQuestionId());
        if (!questionService.isAuthor(memberInfoDto.getMemberId(), question)) {
            throw new AuthenticationException(ErrorCode.UNAUTHORIZED_MEMBER);
        }
        //?????? ?????????
        question.changeContents(patchQuestionRequestDto.getTitle(), patchQuestionRequestDto.getContent());

        //?????? ?????? ?????? ?????????
        question.changeAccessLevel(patchQuestionRequestDto.getAccessLevel());

        //DB ???????????? ????????? ??????????????? ?????? ????????????
        return question.getQuestionId();
    }

    @Transactional(readOnly = true)
    public GetOneQuestionResponseDto getOneQuestion(Long questionId, MemberInfoDto memberInfoDto) {
        Question question = questionService.getOneQuestion(questionId);
        Answer answer = answerService.findByQuestion(questionId);
        //b. ?????? ???????????? ????????? ?????? ????????? ??????
        if (question.getAccessLevel().equals(AccessLevel.PUBLIC)) {
            return GetOneQuestionResponseDto.from(question, answer);
        }
        //a. ?????? ???????????? ??????
        else if (memberInfoDto.getRole().isEmployee()
                || questionService.isAuthor(memberInfoDto.getMemberId(), question)) {
            //1. ?????????, ???????????? 2. ???????????? ??????????????? ??????

            return GetOneQuestionResponseDto.from(question, answer);
        } else {
            throw new AuthenticationException(ErrorCode.UNAUTHORIZED_MEMBER);
        }
    }

    @Transactional(readOnly = true)
    public GetOneQuestionResponseDto getPublicQuestion(Long questionId) {
        Question question = questionService.getOneQuestion(questionId);
        Answer answer = answerService.findByQuestion(questionId);
        if (question.getAccessLevel().equals(AccessLevel.PROTECTED)) {
            throw new AuthenticationException(ErrorCode.NOT_PUBLIC);
        }
        return GetOneQuestionResponseDto.from(question, answer);
    }

    public Long deleteQuestion(MemberInfoDto memberInfoDto, Long questionId) {
        Question question = questionService.getOneQuestion(questionId);
        if (memberInfoDto.getRole().isEmployee()
                || questionService.isAuthor(memberInfoDto.getMemberId(), question)) {
            return questionService.deleteQuestion(question);
        } else {
            throw new AuthenticationException(ErrorCode.UNAUTHORIZED_MEMBER);
        }
    }

    @Transactional(readOnly = true)
    public List<GetOneQuestionResponseDto> getAllQuestions() {
        List<Question> questions = questionService.findAllQuestions();
        List<GetOneQuestionResponseDto> getOneQuestionResponseDtoList =
                questions.stream()
                        .map(q -> GetOneQuestionResponseDto
                                .from(q, answerService.findByQuestion(q.getQuestionId())))
                        .collect(Collectors.toList());
        return getOneQuestionResponseDtoList;
    }

}
