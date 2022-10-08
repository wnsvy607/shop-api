package com.app.api.member.controller;

import com.app.api.member.dto.MemberInfoResponseDto;
import com.app.api.member.service.MemberInfoService;
import com.app.global.jwt.service.TokenManager;
import com.app.global.resolver.memberinfo.MemberInfo;
import com.app.global.resolver.memberinfo.MemberInfoDto;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "member", description = "회원 API")
@RequiredArgsConstructor
@RequestMapping("/api/member")
@RestController
public class MemberInfoController {

    private final TokenManager tokenManager;
    private final MemberInfoService memberInfoService;

    @Tag(name = "member")
    @Operation(summary = "회원 정보 조회 API", description = "회원 정보 조회 API")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 오류 발생"),
            @ApiResponse(responseCode = "M-003", description = "해당 회원은 존재하지 않는 회원입니다.")
    })
    @GetMapping("/info")
    public ResponseEntity<MemberInfoResponseDto> getMemberInfo(
            @MemberInfo MemberInfoDto memberInfoDto)
    {
        Long memberId = memberInfoDto.getMemberId();

        MemberInfoResponseDto memberInfoResponseDto = memberInfoService.getMemberInfo(memberId);

        return ResponseEntity.ok(memberInfoResponseDto);
    }
    @Tag(name = "개발자용 테스트 API")
    @Operation(summary = "모든 회원 정보 조회 API", description = "모든 회원 정보 조회 API")
    @GetMapping("/test")
    public ResponseEntity<List<MemberInfoResponseDto>> getMemberInfo()
    {
        List<MemberInfoResponseDto> allMembers = memberInfoService.getAllMembers();

        return ResponseEntity.ok(allMembers);
    }

}




