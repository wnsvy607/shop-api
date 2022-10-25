package com.app.api.question.dto;

import com.app.domain.answer.entity.Answer;
import com.app.domain.question.constant.AccessLevel;
import com.app.domain.question.constant.AnswerStatus;
import com.app.domain.question.entity.Question;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @Builder
public class GetOneQuestionResponseDto {

    @Schema(description = "문의 글 아이디", example = "1", required = true)
    private Long questionId;

    @Schema(description = "익명화된 작성자 이름", example = "최*섭", required = true)
    private String questionAuthorName;

    @Schema(description = "작성자 Id", example = "1", required = true)
    private Long questionAuthorId;

    @Schema(description = "게시글 제목", example = "배추에서 벌레가 나왔어요", required = true)
    private String title;

    @Schema(description = "게시글 내용", example = "이섭이형 바보", required = true)
    private String questionContent;

    @Schema(description = "접근 레벨", example = "PUBLIC", required = true)
    private AccessLevel accessLevel;

    @Schema(description = "답변 여부", example = "UNANSWERED", required = true)
    private AnswerStatus answerStatus;

    @Schema(description = "답변 아이디", example = "1", required = true)
    private Long answerId;

    @Schema(description = "답변 내용", example = "맞습니다.", required = true)
    private String answerContent;

    @Schema(description = "답변 작성자 이름", example = "맞습니다.", required = true)
    private String answerAuthorName;

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
                .answerId(answer.getAnswerId())
                .answerContent(answer.getContent())
                .answerAuthorName(answerAuthorName)
                .build();
    }
}
