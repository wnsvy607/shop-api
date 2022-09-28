package com.app.api.login.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter @Setter
public class BasicLoginDto {
        @Schema(description = "이메일", example = "test1234@naver.com", required = true)
        private String email;
        @Schema(description = "비밀번호", example = "12345", required = true)
        private String password;
}
