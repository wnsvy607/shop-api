package com.app.api.member.service;

import com.app.api.member.dto.MemberInfoResponseDto;
import com.app.domain.member.entity.Member;
import com.app.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberInfoService {

    private final MemberService memberService;
    @Transactional(readOnly = true)
    public MemberInfoResponseDto getMemberInfo(Long memberId) {
        Member member = memberService.findMemberByMemberId(memberId);
        return MemberInfoResponseDto.from(member);
    }

    @Transactional(readOnly = true)
    public List<MemberInfoResponseDto> getAllMembers() {
        List<Member> members = memberService.findAllMembers();
        List<MemberInfoResponseDto> memberInfoResponseDtos =
                members.stream().map(m -> MemberInfoResponseDto.from(m))
                        .collect(Collectors.toList());
        return memberInfoResponseDtos;
    }
}
