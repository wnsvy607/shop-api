package com.app.api.login.dto;

import com.app.domain.common.Address;
import com.app.domain.member.constant.MemberType;
import com.app.domain.member.constant.Role;
import com.app.domain.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;


@Getter @Setter
public class SignUpDto {

    @Schema(description = "이메일", example = "test1234@naver.com", required = true)
    private String email;
    @Schema(description = "비밀번호", example = "12345", required = true)
    private String password;
    @Schema(description = "유저 이름", example = "홍길동", required = true)
    private String memberName;

    @Schema(description = "연락처", example = "01029267553", required = true)
    private String contact;

    @Schema(description = "생년월일", example = "1997-09-12T", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Schema(description = "회원 주소", example = "",required = true)
    private Address homeAddress;



    public Member toEntity() {
        return Member.builder()
                .memberType(MemberType.LOCAL)
                .email(this.email)
                .password(this.password)
                .memberName(this.memberName)
                .profile("-")
                .role(Role.USER)
                .contact(this.contact)
                .birthDate(this.birthDate)
                .homeAddress(this.homeAddress)
                .build();
    }
}
