package com.app.api.answer.dto;

import com.app.domain.answer.entity.Answer;
import com.app.domain.common.constant.GeneralStatus;
import com.app.domain.member.entity.Member;
import com.app.domain.question.entity.Question;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class PostAnswerRequestDto {

    @ApiModelProperty(notes = "답변 내용", example = "답변", required = true)
    private String content;

    @ApiModelProperty(notes = "답변할 문의글 Id", example = "12", required = true)
    private Long questionId;


    public Answer toEntity(Question question, Member member) {
        return Answer.builder()
                .content(this.content)
                .question(question)
                .member(member)
                .generalStatus(GeneralStatus.ACTIVE)
                .build();
    }
}
