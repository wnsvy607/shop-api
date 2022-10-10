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
    @Operation(summary = "문의글 조회 API", description = "문의글 조회 API")
    @GetMapping("/any/list")
    public ResponseEntity<List<GetQuestionListResponseDto>> getQuestionList(@PageableDefault(size = 10) Pageable pageable) {

        List<GetQuestionListResponseDto> getQuestionListResponseDtoList =
                questionInfoService.getQuestionListDto(pageable);
        return ResponseEntity.ok(getQuestionListResponseDtoList);
    }
    @Tag(name = "question")
    @Operation(summary = "문의글 내용 조회 API", description = "문의글 내용 조회 API")
    @GetMapping("/any/one")
    public ResponseEntity<GetOneQuestionResponseDto> getPublicQuestion(@RequestParam Long questionId) {
        GetOneQuestionResponseDto getOneQuestionResponseDto = questionInfoService.getPublicQuestion(questionId);
        return ResponseEntity.ok(getOneQuestionResponseDto);
    }

    @Tag(name = "question")
    @Operation(summary = "문의글 내용 조회 API(회원)", description = "문의글 내용 조회 API(회원)")
    @GetMapping("/one")
    public ResponseEntity<GetOneQuestionResponseDto> getOneQuestion(@MemberInfo MemberInfoDto memberInfoDto,
                                                                    @RequestParam Long questionId) {
        GetOneQuestionResponseDto getOneQuestionResponseDto = questionInfoService.getOneQuestion(questionId, memberInfoDto);
        return ResponseEntity.ok(getOneQuestionResponseDto);
    }



    @Tag(name = "question")
    @Operation(summary = "문의글 게시 API", description = "문의글 게시 API")
    @PostMapping("")
    public ResponseEntity<Long> postQuestion(@MemberInfo MemberInfoDto memberInfoDto,
                                         @RequestBody PostQuestionRequestDto postQuestionRequestDto){
        return ResponseEntity.ok(questionInfoService.postQuestion(memberInfoDto, postQuestionRequestDto));
    }


    @Tag(name = "question")
    @Operation(summary = "문의글 수정 API", description = "문의글 수정 API")
    @PatchMapping()
    public ResponseEntity<String> modifyQuestion(@MemberInfo MemberInfoDto memberInfoDto,
                                                 @RequestBody PatchQuestionRequestDto patchQuestionRequestDto){
        if(!questionInfoService.modifyQuestion(memberInfoDto, patchQuestionRequestDto)){
            throw new BusinessException(ErrorCode.UPDATE_FAILED);
        }
        String result = "게시물 정보 수정을 완료하였습니다.";
        return ResponseEntity.ok(result);
    }

//    @PatchMapping
//    public ResponseEntity<> deleteQuestion(){
//        //
//    }
}
