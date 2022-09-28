package com.app.api.login.dto;

import com.app.global.jwt.dto.JwtTokenDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;

@Getter
@Setter
public class OauthLoginDto {
    @Schema(description = "소셜 로그인 회원 타입", example = "KAKAO", required = true)
    private String memberType;
}
