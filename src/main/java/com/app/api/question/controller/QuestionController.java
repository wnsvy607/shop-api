package com.app.api.question.controller;

import com.app.api.question.dto.GetQuestionListResponseDto;
import com.app.api.question.dto.PostQuestionRequestDto;
import com.app.api.question.service.QuestionInfoService;
import com.app.domain.question.service.QuestionService;
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
    @Operation(summary = "문의글 조회 API", description = "문의글 조회 API")
    @GetMapping("/list")
    public ResponseEntity<List<GetQuestionListResponseDto>> getQuestionList(@PageableDefault(size = 10) Pageable pageable) {

        List<GetQuestionListResponseDto> getQuestionListResponseDtoList =
                questionInfoService.getQuestionListDto(pageable);
        return ResponseEntity.ok(getQuestionListResponseDtoList);
    }

//    @GetMapping()
//    public ResponseEntity<QuestionListResponseDto> getOneQuestion(@MemberInfo MemberInfoDto memberInfoDto) {
//          //a. 비밀 게시글일 경우
//          //1. 어드민인지 2. 아니라면 작성자인지 검증
//          //b. 아니라면 모두 열람이 가능
//
//    }

    @Tag(name = "question")
    @Operation(summary = "문의글 게시 API", description = "문의글 게시 API")
    @PostMapping("")
    public ResponseEntity<Long> postQuestion(@MemberInfo MemberInfoDto memberInfoDto,
                                         @RequestBody PostQuestionRequestDto postQuestionRequestDto){
        return ResponseEntity.ok(questionInfoService.postQuestion(memberInfoDto, postQuestionRequestDto));
    }

//    @PatchMapping()
//    public ResponseEntity<> modifyQuestion(){
//        //
//    }
//
//    @PatchMapping
//    public ResponseEntity<> deleteQuestion(){
//        //
//    }
}
