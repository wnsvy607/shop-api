package com.app.api.question.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetQuestionPageInfoDto {

    @Schema(description = "총 페이지 수", example = "5", required = true)
    private Integer totalPageNumber;

    private List<GetQuestionListResponseDto> getQuestionListResponseDtoList;

    public static GetQuestionPageInfoDto of(List<GetQuestionListResponseDto> getQuestionListResponseDtoList,
                                            Integer totalPageNumber) {
        return GetQuestionPageInfoDto.builder()
                .totalPageNumber(totalPageNumber)
                .getQuestionListResponseDtoList(getQuestionListResponseDtoList)
                .build();
    }
}
