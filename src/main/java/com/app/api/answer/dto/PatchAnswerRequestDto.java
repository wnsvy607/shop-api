package com.app.api.answer.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class PatchAnswerRequestDto {

    @Schema(description = "답변 아이디", example = "1", required = true)
    private Long answerId;

    @NotBlank
    @ApiModelProperty(notes = "변경할 답변 내용", example = "변경할 내용", required = true)
    private String content;

}
