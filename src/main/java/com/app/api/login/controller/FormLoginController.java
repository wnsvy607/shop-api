package com.app.api.login.controller;

import com.app.api.login.dto.BasicLoginDto;
import com.app.api.login.dto.LoginResponseDto;
import com.app.api.login.dto.SignUpDto;
import com.app.api.login.service.FormLoginService;
import com.app.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "authentication", description = "로그인/회원가입/로그아웃/토큰재발급 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/basic")
public class FormLoginController {

    private final FormLoginService formLoginService;
    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;

    @Tag(name = "authentication")
    @Operation(summary = "폼 로그인 API", description = "폼 로그인 API")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 오류 발생"),
            @ApiResponse(responseCode = "M-003", description = "해당 회원은 존재하지 않습니다."),
            @ApiResponse(responseCode = "A-009", description = "패스워드가 올바르지 않습니다.")
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> formLogin(@RequestBody BasicLoginDto formLoginRequestDto){
        //회원가입은 이미 되어있다고 가정.
        LoginResponseDto jwtTokenResponseDto = formLoginService.formLogin(formLoginRequestDto);

        return ResponseEntity.ok(jwtTokenResponseDto);
    }


    @Tag(name = "authentication")
    @Operation(summary = "회원 가입 API", description = "회원 가입 API")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 오류 발생"),
            @ApiResponse(responseCode = "M-002", description = "이미 가입된 회원 입니다. MemberType: LOCAL")
    })
    @PostMapping("/signup")
    public ResponseEntity<String> singUp(@RequestBody SignUpDto signUpDto){

        String encodePasswd = passwordEncoder.encode(signUpDto.getPassword());
        signUpDto.setPassword(encodePasswd);

        Long memberId = memberService.registerMember(signUpDto.toEntity()).getMemberId();
        String result = "memberId : " + memberId;
        return ResponseEntity.ok(result);
    }
}
