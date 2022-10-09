package com.app.api.question.dto;

import com.app.domain.question.constant.AccessLevel;
import com.app.domain.question.entity.Question;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class PatchQuestionRequestDto {

    @Schema(description = "문의 글 아이디", example = "1", required = true)
    private Long questionId;

    @Schema(description = "게시글 제목", example = "배추에서 벌레가 나왔어요", required = true)
    private String title;

    @Schema(description = "게시글 내용", example = "아오 슈발", required = true)
    private String content;

    @Schema(description = "게시글 내용", example = "아오 슈발", required = false)
    private AccessLevel accessLevel;

}
