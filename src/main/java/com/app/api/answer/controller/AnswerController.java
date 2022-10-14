package com.app.api.answer.controller;

import com.app.api.answer.dto.PostAnswerRequestDto;
import com.app.domain.answer.service.AnswerService;
import com.app.global.resolver.memberinfo.MemberInfo;
import com.app.global.resolver.memberinfo.MemberInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "answer", description = "답변 조회/생성/수정/삭제 API")
@RestController
@RequestMapping("/api/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @Tag(name = "answer")
    @Operation(summary = "답변 게시 API", description = "답변 글 작성 API - 관리자, 직원만 가능")
    @PostMapping("")
    public ResponseEntity<Long> postAnswer(@MemberInfo MemberInfoDto memberInfoDto,
                                           @RequestBody PostAnswerRequestDto postAnswerRequestDto) {
        // 관리자, 직원 롤인지 체크하는 인터셉터 추가 필요
        Long answerId = answerService.postAnswer(memberInfoDto, postAnswerRequestDto);

        return ResponseEntity.ok(answerId);
    }


}
