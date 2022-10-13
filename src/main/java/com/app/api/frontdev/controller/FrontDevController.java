package com.app.api.frontdev.controller;

import com.app.api.member.dto.MemberInfoResponseDto;
import com.app.api.member.service.MemberInfoService;
import com.app.api.question.dto.GetOneQuestionResponseDto;
import com.app.api.question.dto.GetQuestionListResponseDto;
import com.app.api.question.service.QuestionInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "개발자용 테스트 API",description = "프론트 개발자용 API")
@RequiredArgsConstructor
@RequestMapping("api/front")
@RestController
public class FrontDevController {

    private final MemberInfoService memberInfoService;
    private final QuestionInfoService questionInfoService;

    @Tag(name = "개발자용 테스트 API")
    @Operation(summary = "모든 회원 정보 조회 API", description = "모든 회원 리스트 조회, 비밀번호는 조회안됨(암호화 되어 있음)")
    @GetMapping("/members")
    public ResponseEntity<List<MemberInfoResponseDto>> getMemberInfo()
    {
        List<MemberInfoResponseDto> allMembers = memberInfoService.getAllMembers();
        return ResponseEntity.ok(allMembers);
    }

    @Tag(name = "개발자용 테스트 API")
    @Operation(summary = "모든 문의 글 정보 조회 API", description = "모든 문의 글 정보 조회 API")
    @GetMapping("/questions")
    public ResponseEntity<List<GetOneQuestionResponseDto>> getQuestionInfo()
    {
        List<GetOneQuestionResponseDto> allQuestions = questionInfoService.getAllQuestions();
        return ResponseEntity.ok(allQuestions);
    }
}
