package com.app.domain.question.entity;

import com.app.domain.answer.entity.Answer;
import com.app.domain.common.BaseEntity;
import com.app.domain.member.entity.Member;
import com.app.domain.question.constant.AnswerStatus;
import com.app.domain.question.constant.AccessLevel;
import com.app.domain.common.constant.GeneralStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Question extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 300, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private GeneralStatus generalStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private AccessLevel accessLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private AnswerStatus answerStatus;

    @Builder
    public Question(String title, String content, Member member, GeneralStatus generalStatus, AccessLevel accessLevel, AnswerStatus answerStatus) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.generalStatus = generalStatus;
        this.accessLevel = accessLevel;
        this.answerStatus = answerStatus;
    }

    public void changeContents(String title, String content){
        this.title = title;
        this.content = content;
    }

    //삭제시 사용
    public void delete(){
        this.generalStatus = GeneralStatus.INACTIVE;
    }

    //쓰일지는 미지수(비밀글이냐 아니냐 설정)
    public void changeAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    //답변 등록시, 답변 삭제시 사용
    public void changeAnswerStatus(AnswerStatus answerStatus) {
        this.answerStatus = answerStatus;
    }


}
