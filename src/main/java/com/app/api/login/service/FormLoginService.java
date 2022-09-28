package com.app.api.login.service;

import com.app.api.login.dto.BasicLoginDto;
import com.app.api.login.dto.LoginResponseDto;
import com.app.domain.member.entity.Member;
import com.app.domain.member.service.MemberService;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.AuthenticationException;
import com.app.global.error.exception.EntityNotFoundException;
import com.app.global.jwt.dto.JwtTokenDto;
import com.app.global.jwt.service.TokenManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class FormLoginService {

    private final TokenManager tokenManager;
    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;

    public LoginResponseDto formLogin(BasicLoginDto request) {
        Member member = memberService.findMemberByEmail(request.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_EXISTS));
        //패스워드 검증
        if(!passwordEncoder.matches(request.getPassword(), member.getPassword())){
            throw new AuthenticationException(ErrorCode.INVALID_PASSWORD);
        }

        JwtTokenDto jwtTokenDto = tokenManager.createJwtTokenDto(member.getMemberId(), member.getRole());
        member.updateRefreshToken(jwtTokenDto);

        return LoginResponseDto.of(jwtTokenDto);
    }

}
