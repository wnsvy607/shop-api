package com.app.api.login.controller;

import com.app.api.login.dto.LoginResponseDto;
import com.app.api.login.dto.OauthLoginDto;
import com.app.api.login.service.OauthLoginService;
import com.app.api.login.validator.OauthValidator;
import com.app.domain.member.constant.MemberType;
import com.app.global.util.AuthorizationHeaderUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Tag(name = "authentication", description = "로그인/로그아웃/토큰재발급 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth")
public class OauthLoginController {

    private final OauthValidator oauthValidator;
    private final OauthLoginService oauthLoginService;

    @Tag(name = "authentication")
    @Operation(summary = "소셜 로그인 API", description = "소셜 로그인 API")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> oauthLogin(@RequestBody OauthLoginDto oauthLoginRequestDto,
                                                       HttpServletRequest httpServletRequest){

        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        AuthorizationHeaderUtils.validateAuthorization(authorizationHeader);
        oauthValidator.validateMemberType(oauthLoginRequestDto.getMemberType());

        String accessToken = authorizationHeader.split(" ")[1];
        LoginResponseDto jwtTokenResponseDto = oauthLoginService
                .oauthLogin(accessToken, MemberType.from(oauthLoginRequestDto.getMemberType()));

        return ResponseEntity.ok(jwtTokenResponseDto);
    }
}
