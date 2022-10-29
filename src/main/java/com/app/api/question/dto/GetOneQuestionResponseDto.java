package com.app.api.question.dto;

import com.app.domain.answer.entity.Answer;
import com.app.domain.question.constant.AccessLevel;
import com.app.domain.question.constant.AnswerStatus;
import com.app.domain.question.entity.Question;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Builder
public class GetOneQuestionResponseDto {

    @Schema(description = "문의 글 아이디", example = "1")
    private Long questionId;

    @Schema(description = "익명화된 작성자 이름", example = "최*섭")
    private String questionAuthorName;

    @Schema(description = "작성자 Id", example = "1")
    private Long questionAuthorId;

    @Schema(description = "게시글 제목", example = "배추에서 벌레가 나왔어요")
    private String title;

    @Schema(description = "게시글 내용", example = "이섭이형 바보")
    private String questionContent;

    @Schema(description = "접근 레벨", example = "PUBLIC")
    private AccessLevel accessLevel;

    @Schema(description = "답변 여부", example = "UNANSWERED")
    private AnswerStatus answerStatus;

    @Schema(description = "문의 작성 시간", example = "")
    private LocalDateTime questionCreateTime;

    @Schema(description = "답변 아이디", example = "1")
    private Long answerId;

    @Schema(description = "답변 내용", example = "맞습니다.")
    private String answerContent;

    @Schema(description = "답변 작성자 이름", example = "최이섭")
    private String answerAuthorName;

    @Schema(description = "답변 작성 시간", example = "")
    private LocalDateTime answerCreateTime;

    public static GetOneQuestionResponseDto from(Question question,Answer answer) {
        String questionAuthorName = question.getMember().getBlindedName();
        String answerAuthorName = answer.getMember().getBlindedName();

        return GetOneQuestionResponseDto.builder()
                .questionId(question.getQuestionId())
                .questionAuthorName(questionAuthorName)
                .questionAuthorId(question.getMember().getMemberId())
                .title(question.getTitle())
                .questionContent(question.getContent())
                .accessLevel(question.getAccessLevel())
                .answerStatus(question.getAnswerStatus())
                .questionCreateTime(question.getCreateTime())
                .answerId(answer.getAnswerId())
                .answerContent(answer.getContent())
                .answerAuthorName(answerAuthorName)
                .answerCreateTime(answer.getCreateTime())
                .build();
    }
}
