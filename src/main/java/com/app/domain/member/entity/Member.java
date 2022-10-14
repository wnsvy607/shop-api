package com.app.domain.member.entity;

import com.app.domain.common.Address;
import com.app.domain.common.BaseTimeEntity;
import com.app.domain.member.constant.MemberType;
import com.app.domain.member.constant.Role;
import com.app.global.jwt.dto.JwtTokenDto;
import com.app.global.util.DateTimeUtils;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private MemberType memberType;

    @Column(unique = true, length = 50, nullable = false)
    private String email;

    @Column(length = 200)
    private String password;

    @Column(nullable = false, length = 20)
    private String memberName;

    @Column(length = 200)
    private String profile;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role role;

    @Embedded
    private Address homeAddress;

    @Column(length = 15)
    private String contact;

    private LocalDate birthDate;

    @Column(length = 250)
    private String refreshToken;

    private LocalDateTime tokenExpirationTime;

    @Builder
    public Member(MemberType memberType, String email, String password, String memberName, String profile, Role role, Address homeAddress, String contact, LocalDate birthDate) {
        this.memberType = memberType;
        this.email = email;
        this.password = password;
        this.memberName = memberName;
        this.profile = profile;
        this.role = role;
        this.homeAddress = homeAddress;
        this.contact = contact;
        this.birthDate = birthDate;
    }


    public void updateRefreshToken(JwtTokenDto jwtTokenDto) {
        this.refreshToken = jwtTokenDto.getRefreshToken();
        this.tokenExpirationTime = DateTimeUtils.convertToLocalDateTime(jwtTokenDto.getRefreshTokenExpireTime());
    }

    public void expireRefreshToken(LocalDateTime now) {
        this.tokenExpirationTime = now;
    }

    public String getBlindedName(){
        String Name = this.getMemberName();
        int length = Name.length();
        String blindedName = Name.substring(0, 1);
        if (length > 2) {
            for (int i = 0; i < length - 2; i++) {
                blindedName += '*';
            }
            blindedName += Name.substring(length - 1);
        } else if (length == 2) {
            blindedName += '*';
        }
        return blindedName;
    }

    public static Member notExistMember() {
        return Member.builder()
                .memberName("없는 회원")
                .build();
    }
}
