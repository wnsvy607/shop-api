package com.app.api.answer.controller;

import com.app.api.answer.dto.PatchAnswerRequestDto;
import com.app.api.answer.dto.PostAnswerRequestDto;
import com.app.api.answer.service.AnswerInfoService;
import com.app.api.question.dto.PatchQuestionRequestDto;
import com.app.domain.answer.service.AnswerService;
import com.app.global.resolver.memberinfo.MemberInfo;
import com.app.global.resolver.memberinfo.MemberInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "answer", description = "답변 생성/수정/삭제 API")
@RestController
@RequestMapping("/api/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerInfoService answerInfoService;

    @Tag(name = "answer")
    @Operation(summary = "답변 게시 API", description = "답변 글 작성 API - 관리자, 직원만 가능")
    @PostMapping()
    public ResponseEntity<Long> postAnswer(@MemberInfo MemberInfoDto memberInfoDto,
                                           @RequestBody PostAnswerRequestDto postAnswerRequestDto) {
        Long answerId = answerInfoService.postAnswer(memberInfoDto, postAnswerRequestDto);

        return ResponseEntity.ok(answerId);
    }

    @Tag(name = "answer")
    @Operation(summary = "답변 수정 API", description = "답변 글 수정 API - 관리자, 직원만 가능")
    @PatchMapping()
    public ResponseEntity<Long> modifyAnswer(@RequestBody PatchAnswerRequestDto patchAnswerRequestDto) {
        Long answerId = answerInfoService.modifyAnswer(patchAnswerRequestDto);

        return ResponseEntity.ok(answerId);
    }

    @Tag(name = "answer")
    @Operation(summary = "답변 삭제 API", description = "답변 글 삭제 API - 관리자, 직원만 가능")
    @PatchMapping("/status")
    public ResponseEntity<Long> deleteAnswer(@RequestParam Long answerId) {
        Long deletedId = answerInfoService.delete(answerId);
        return ResponseEntity.ok(deletedId);
    }
}
