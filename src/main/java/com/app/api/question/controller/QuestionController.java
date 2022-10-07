package com.app.api.question.controller;

import com.app.api.question.dto.QuestionListResponseDto;
import com.app.domain.question.service.QuestionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "question", description = "문의 글 API")
@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/list")
    public ResponseEntity<List<QuestionListResponseDto>> getQuestionList(Pageable pageable) {

        List<QuestionListResponseDto> questionListResponseDtoList = questionService.getQuestionList(pageable).stream().
                map(QuestionListResponseDto::from).collect(Collectors.toList());
        return ResponseEntity.ok(questionListResponseDtoList);
    }

//    @GetMapping()
//    public ResponseEntity<QuestionListResponseDto> getOneQuestion(@MemberInfo MemberInfoDto memberInfoDto) {
//          //비밀 게시글일 경우 유저정보 검증하는 로직
//
            //
//    }
//
//    @PostMapping()
//    public ResponseEntity<> postQuestion(){
//        //
//    }
//
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
