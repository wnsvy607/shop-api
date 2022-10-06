package com.app.global.error.exception;

import com.app.domain.member.constant.MemberType;
import com.app.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class MemberDuplicationException extends BusinessException {

    private MemberType memberType;
    public MemberDuplicationException(ErrorCode errorCode,MemberType memberType) {
        super(errorCode);
        this.memberType = memberType;
    }
}
