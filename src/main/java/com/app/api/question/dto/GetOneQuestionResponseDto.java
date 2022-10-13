package com.app.api.question.dto;

import com.app.domain.question.constant.AccessLevel;
import com.app.domain.question.constant.AnswerStatus;
import com.app.domain.question.entity.Question;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class GetOneQuestionResponseDto {

    @Schema(description = "문의 글 아이디", example = "1", required = true)
    private Long questionId;

    @Schema(description = "익명화된 작성자 이름", example = "최*섭", required = true)
    private String authorName;

    @Schema(description = "게시글 제목", example = "배추에서 벌레가 나왔어요", required = true)
    private String title;

    @Schema(description = "게시글 내용", example = "게시글 내용", required = true)
    private String content;

    @Schema(description = "접근 레벨", example = "PUBLIC", required = true)
    private AccessLevel accessLevel;

    @Schema(description = "답변 여부", example = "UNANSWERED", required = true)
    private AnswerStatus answerStatus;

//   Todo 답변 관련 필드 추가 해야됨
    public static GetOneQuestionResponseDto from(Question question) {
        String authorName = question.getMember().getBlindedName();

        return GetOneQuestionResponseDto.builder()
                .questionId(question.getQuestionId())
                .authorName(authorName)
                .title(question.getTitle())
                .content(question.getContent())
                .accessLevel(question.getAccessLevel())
                .answerStatus(question.getAnswerStatus())
                .build();
    }
}
