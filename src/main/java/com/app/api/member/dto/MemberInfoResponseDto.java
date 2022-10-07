package com.app.api.member.dto;

import com.app.domain.common.Address;
import com.app.domain.member.constant.Role;
import com.app.domain.member.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter @Builder
public class MemberInfoResponseDto {

    @Schema(description = "회원 아이디", example = "1",required = true)
    private Long memberId;
    @Schema(description = "이메일", example = "test@gamil.com",required = true)
    private String email;
    @Schema(description = "회원 이름", example = "홍길동",required = true)
    private String memberName;
    @Schema(description = "프로필 이미지 경로", example = "http://k.kakaocdn.net/img_110x110.jpg",required = false)
    private String profile;
    @Schema(description = "회원의 역할", example = "USER",required = true)
    private Role role;
    @Schema(description = "회원 주소", example = "USER", required = true)
    private Address homeAddress;
    @Schema(description = "생년월일", example = "2022-10-02", required = true)
    private LocalDate birthDate;
    @Schema(description = "회원 연락처", example = "01029267553", required = true)
    private String contact;

    public static MemberInfoResponseDto from(Member member) {
        return MemberInfoResponseDto.builder()
                .memberId(member.getMemberId())
                .memberName(member.getMemberName())
                .email(member.getEmail())
                .profile(member.getProfile())
                .homeAddress(member.getHomeAddress())
                .role(member.getRole())
                .birthDate(member.getBirthDate())
                .contact(member.getContact())
                .build();
    }
}
