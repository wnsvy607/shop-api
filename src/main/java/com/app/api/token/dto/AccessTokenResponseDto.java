package com.app.api.token.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter @Builder
public class AccessTokenResponseDto {
    @Schema(description = "grantType", example = "Bearer", required = true)
    private String grantType;

    @Schema(description = "access Token", example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBQ0NFU1MiLCJpYXQiOjE2NjM5MTczNjQsImV4cCI6MTY2MzkxODI2NCwibWVtYmVySWQiOjEsInJvbGUiOiJBRE1JTiJ9.WVqT2UR9qSh-CqoBbq0xkUOKRYOFIwpWKeDCKMzlXXwXFie7Zt8laaDij6X2R4KxTtDNSoLoMbFkYB4h-M-wHQ", required = true)
    private String accessToken;

    @Schema(description = "access Token 만료 시간", example = "2022-09-23 16:31:04", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date accessTokenExpireTime;

}
