package com.app.api.login.dto;

import com.app.global.jwt.dto.JwtTokenDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
    @Schema(description = "grantType", example = "Bearer", required = true)
    private String grantType;

    @Schema(description = "access Token", example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBQ0NFU1MiLCJpYXQiOjE2NjM5MTczNjQsImV4cCI6MTY2MzkxODI2NCwibWVtYmVySWQiOjEsInJvbGUiOiJBRE1JTiJ9.WVqT2UR9qSh-CqoBbq0xkUOKRYOFIwpWKeDCKMzlXXwXFie7Zt8laaDij6X2R4KxTtDNSoLoMbFkYB4h-M-wHQ", required = true)
    private String accessToken;

    @Schema(description = "access Token 만료 시간", example = "2022-09-23 16:31:04", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date accessTokenExpireTime;

    @Schema(description = "refresh Token", example = "eyJdeXAiOiJK12iLCJhsGciOiJIUzUxMiJ9.eyJzdWIiO1wJSRUZSRVNIIiwiaWF0IjoxNjYzOTE3MzY0LCJleHAiOjE2NjUxMjY5NjQsIm1lbWJlcklkIjoxfQ.rbZks293zQ7EEeFpvXNwD98qGx2gwfFH-Df0dCyTp733x6jl68lrqe_eV1A1l-Ld91Bl3zNoA_32WPNi55pjtw", required = true)
    private String refreshToken;

    @Schema(description = "refresh Token 만료 시간", example = "2022-10-07 16:16:0", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date refreshTokenExpireTime;

    public static LoginResponseDto of(JwtTokenDto jwtTokenDto){
        return LoginResponseDto.builder()
                .grantType(jwtTokenDto.getGrantType())
                .accessToken(jwtTokenDto.getAccessToken())
                .accessTokenExpireTime(jwtTokenDto.getAccessTokenExpireTime())
                .refreshToken(jwtTokenDto.getRefreshToken())
                .refreshTokenExpireTime(jwtTokenDto.getRefreshTokenExpireTime())
                .build();
    }
}
