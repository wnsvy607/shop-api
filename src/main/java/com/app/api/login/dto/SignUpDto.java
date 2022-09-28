package com.app.api.login.dto;

import com.app.domain.member.constant.MemberType;
import com.app.domain.member.constant.Role;
import com.app.domain.member.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter @Setter
public class SignUpDto {

    @Schema(description = "이메일", example = "test1234@naver.com", required = true)
    private String email;
    @Schema(description = "비밀번호", example = "12345", required = true)
    private String password;
    @Schema(description = "유저 이름", example = "홍길동", required = true)
    private String memberName;


    public Member toEntity() {
        return Member.builder()
                .memberType(MemberType.LOCAL)
                .email(this.email)
                .password(this.password)
                .memberName(this.memberName)
                .profile(" ")
                .role(Role.USER)
                .build();
    }
}
