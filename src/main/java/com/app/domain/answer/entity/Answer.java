package com.app.domain.answer.entity;

import com.app.domain.common.constant.GeneralStatus;
import com.app.domain.member.entity.Member;
import com.app.domain.question.entity.Question;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Member member;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private GeneralStatus generalStatus;

    @Builder
    public Answer(Question question, Member member, String content, GeneralStatus generalStatus) {
        this.question = question;
        this.member = member;
        this.content = content;
        this.generalStatus = generalStatus;
    }

    public void changeContents(String content){
        this.content = content;
    }
    public void delete(){
        this.generalStatus = GeneralStatus.INACTIVE;
    }

}
