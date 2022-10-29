package com.app.api.question.dto;

import com.app.domain.common.constant.GeneralStatus;
import com.app.domain.member.entity.Member;
import com.app.domain.question.constant.AnswerStatus;
import com.app.domain.question.constant.AccessLevel;
import com.app.domain.question.entity.Question;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class PostQuestionRequestDto {

    @ApiModelProperty(notes = "게시글 제목", example = "제목", required = true)
    private String title;
    @ApiModelProperty(notes = "게시글 내용", example = "내용", required = true)
    private String content;
    @ApiModelProperty(notes = "비밀글 여부", example = "PUBLIC", required = true)
    private AccessLevel accessLevel;

    public Question toEntity(Member member) {
        return Question.builder()
                .member(member)
                .title(this.title)
                .content(this.content)
                .accessLevel(this.accessLevel)
                .generalStatus(GeneralStatus.ACTIVE)
                .answerStatus(AnswerStatus.UNANSWERED)
                .build();
    }

}
