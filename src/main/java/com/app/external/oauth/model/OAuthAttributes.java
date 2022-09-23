package com.app.external.oauth.model;

import com.app.domain.member.constant.MemberType;
import com.app.domain.member.constant.Role;
import com.app.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

//회원가입을 위한 클래스로, 각기 다른 플랫폼의 회원정보를 일원화시키기 위해서 사용
@ToString
@Getter @Builder
public class OAuthAttributes {

    private String name;
    private String email;
    private String profile;
    private MemberType memberType;

    public Member toMemberEntity(MemberType memberType, Role role) {
        return Member.builder()
                .memberName(name)
                .email(email)
                .profile(profile)
                .memberType(memberType)
                .role(role)
                .build();
    }
}
