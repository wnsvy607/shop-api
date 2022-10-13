package com.app.api.question.service;

import com.app.api.question.dto.GetOneQuestionResponseDto;
import com.app.api.question.dto.GetQuestionListResponseDto;
import com.app.api.question.dto.PatchQuestionRequestDto;
import com.app.api.question.dto.PostQuestionRequestDto;
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
    private final QuestionRepository questionRepository;
    private final MemberService memberService;


    public Long postQuestion(MemberInfoDto memberInfoDto,
                             PostQuestionRequestDto postQuestionRequestDto) {
        Member member = memberService.findValidMember(memberInfoDto.getMemberId());
        Question question = postQuestionRequestDto.toEntity(member);
        return questionRepository.save(question).getQuestionId();
    }

    public List<GetQuestionListResponseDto> getQuestionListDto(Pageable pageable) {
        return questionService.getQuestionList(pageable).stream().
                map(GetQuestionListResponseDto::from).collect(Collectors.toList());
    }

    public Long modifyQuestion(MemberInfoDto memberInfoDto, PatchQuestionRequestDto patchQuestionRequestDto) {
        Question question = questionService.getOneQuestion(patchQuestionRequestDto.getQuestionId());
        if (!questionService.isAuthor(memberInfoDto.getMemberId(), question)) {
            throw new AuthenticationException(ErrorCode.UNAUTHORIZED_MEMBER);
        }
        //내용 바꾸기
        question.changeContents(patchQuestionRequestDto.getTitle(), patchQuestionRequestDto.getContent());

        //접근 권한 수준 바꾸기
        question.changeAccessLevel(patchQuestionRequestDto.getAccessLevel());

        //DB 업데이트 과정은 더티체킹을 통해 이루어짐
        return question.getQuestionId();
    }

    @Transactional(readOnly = true)
    public GetOneQuestionResponseDto getOneQuestion(Long questionId, MemberInfoDto memberInfoDto) {
        Question question = questionService.getOneQuestion(questionId);
        //b. 비밀 게시물이 아니면 모두 열람이 가능
        if (question.getAccessLevel().equals(AccessLevel.PUBLIC)) {
            return GetOneQuestionResponseDto.from(question);
        }
        //a. 비밀 게시글일 경우
        else if (memberInfoDto.getRole().isEmployee()
                || questionService.isAuthor(memberInfoDto.getMemberId(), question)) {
            //1. 어드민, 직원인지 2. 아니라면 작성자인지 검증

            return GetOneQuestionResponseDto.from(question);
        } else {
            throw new AuthenticationException(ErrorCode.UNAUTHORIZED_MEMBER);
        }
    }

    @Transactional(readOnly = true)
    public GetOneQuestionResponseDto getPublicQuestion(Long questionId) {
        Question question = questionService.getOneQuestion(questionId);
        if (question.getAccessLevel().equals(AccessLevel.PROTECTED)) {
            throw new AuthenticationException(ErrorCode.NOT_PUBLIC);
        }
        return GetOneQuestionResponseDto.from(question);
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
                questions.stream().map(m -> GetOneQuestionResponseDto.from(m))
                .collect(Collectors.toList());
        return getOneQuestionResponseDtoList;
    }

}
