package com.app.api.question.controller;

import com.app.api.question.dto.GetOneQuestionResponseDto;
import com.app.api.question.dto.GetQuestionListResponseDto;
import com.app.api.question.dto.PatchQuestionRequestDto;
import com.app.api.question.dto.PostQuestionRequestDto;
import com.app.api.question.service.QuestionInfoService;
import com.app.domain.question.entity.Question;
import com.app.domain.question.service.QuestionService;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.BusinessException;
import com.app.global.resolver.memberinfo.MemberInfo;
import com.app.global.resolver.memberinfo.MemberInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "question", description = "문의글 조회/생성/수정/삭제 API")
@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionInfoService questionInfoService;

    @Tag(name = "question")
    @Operation(summary = "문의글 조회 API", description = "문의 글을 페이지 단위로 조회 (내용은 제외) - 로그인 불필요")
    @GetMapping("/any/list")
    public ResponseEntity<List<GetQuestionListResponseDto>> getQuestionList(@PageableDefault(size = 10) Pageable pageable) {

        List<GetQuestionListResponseDto> getQuestionListResponseDtoList =
                questionInfoService.getQuestionListDto(pageable);
        return ResponseEntity.ok(getQuestionListResponseDtoList);
    }

    @Tag(name = "question")
    @Operation(summary = "문의글 내용 조회 API", description = "문의 글 하나의 내용 조회 API  - 로그인 불필요")
    @GetMapping("/any/one")
    public ResponseEntity<GetOneQuestionResponseDto> getPublicQuestion(@RequestParam Long questionId) {
        GetOneQuestionResponseDto getOneQuestionResponseDto = questionInfoService.getPublicQuestion(questionId);
        return ResponseEntity.ok(getOneQuestionResponseDto);
    }

    @Tag(name = "question")
    @Operation(summary = "문의글 내용 조회 API(회원)", description = "문의 글 하나의 내용 조회 API(회원) - 로그인 필요")
    @GetMapping("/one")
    public ResponseEntity<GetOneQuestionResponseDto> getOneQuestion(@MemberInfo MemberInfoDto memberInfoDto,
                                                                    @RequestParam Long questionId) {
        GetOneQuestionResponseDto getOneQuestionResponseDto = questionInfoService.getOneQuestion(questionId, memberInfoDto);
        return ResponseEntity.ok(getOneQuestionResponseDto);
    }


    @Tag(name = "question")
    @Operation(summary = "문의글 게시 API", description = "문의 글 작성 API - 로그인 필요")
    @PostMapping("")
    public ResponseEntity<Long> postQuestion(@MemberInfo MemberInfoDto memberInfoDto,
                                             @RequestBody PostQuestionRequestDto postQuestionRequestDto) {
        return ResponseEntity.ok(questionInfoService.postQuestion(memberInfoDto, postQuestionRequestDto));
    }


    @Tag(name = "question")
    @Operation(summary = "문의글 수정 API", description = "작성자만 사용 가능")
    @PatchMapping()
    public ResponseEntity<String> modifyQuestion(@MemberInfo MemberInfoDto memberInfoDto,
                                                 @RequestBody PatchQuestionRequestDto patchQuestionRequestDto) {
        Long modifiedQuestionId = questionInfoService.modifyQuestion(memberInfoDto, patchQuestionRequestDto);

        String result = "게시물 정보 수정을 완료하였습니다. ID: " + modifiedQuestionId;
        return ResponseEntity.ok(result);
    }
    @Tag(name = "question")
    @Operation(summary = "문의글 삭제 API", description = "작성자, 관리자만 사용 가능")
    @PatchMapping("/status")
    public ResponseEntity<String> deleteQuestion(@MemberInfo MemberInfoDto memberInfoDto,
                                                 @RequestParam Long questionId) {
        Long deletedQuestionId = questionInfoService.deleteQuestion(memberInfoDto, questionId);
        String result = "게시물 삭제를 완료하였습니다. ID: " + deletedQuestionId;
        return ResponseEntity.ok(result);
    }
}
