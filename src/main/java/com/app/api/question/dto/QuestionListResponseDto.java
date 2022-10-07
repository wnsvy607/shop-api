package com.app.api.question.dto;

import com.app.domain.common.constant.GeneralStatus;
import com.app.domain.question.constant.AnswerStatus;
import com.app.domain.question.constant.QuestionAccess;
import com.app.domain.question.entity.Question;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @Builder
public class QuestionListResponseDto {

    @Schema(description = "문의 아이디", example = "1",required = true)
    private Long questionId;

    private String authorName;

    @Schema(description = "회원 아이디", example = "1",required = true)
    private String title;


    @Schema(description = "회원 아이디", example = "1",required = true)
    private String content;

    @Schema(description = "회원 아이디", example = "1",required = true)
    private QuestionAccess questionAccess;

    @Schema(description = "회원 아이디", example = "1",required = true)
    private AnswerStatus answerStatus;

    public static QuestionListResponseDto from(Question question) {
        String authorName = question.getMember().getMemberName();
        // 이름인지 닉네임인지?
        return QuestionListResponseDto.builder()
                .questionId(question.getQuestionId())
                .authorName(authorName)
                .title(question.getTitle())
                .content(question.getContent())
                .questionAccess(question.getQuestionAccess())
                .answerStatus(question.getAnswerStatus())
                .build();
    }

}
