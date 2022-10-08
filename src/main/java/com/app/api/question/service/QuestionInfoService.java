package com.app.api.question.service;

import com.app.api.question.dto.GetQuestionListResponseDto;
import com.app.api.question.dto.PostQuestionRequestDto;
import com.app.domain.member.entity.Member;
import com.app.domain.member.service.MemberService;
import com.app.domain.question.entity.Question;
import com.app.domain.question.repository.QuestionRepository;
import com.app.domain.question.service.QuestionService;
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

//    문의글 리스트 조회 메서드
//    public List<GetQuestionListResponseDto> getQuestionListResponseDto() {
//
//    }

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
}
