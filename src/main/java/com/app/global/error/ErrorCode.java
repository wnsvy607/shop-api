package com.app.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    TEST(HttpStatus.INTERNAL_SERVER_ERROR, "001","business exception test"),

    //인증
    //401 에러
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED,"A-001","토큰이 만료되었습니다."),
    NOT_VALID_TOKEN(HttpStatus.UNAUTHORIZED,"A-002" , "해당 토큰은 유효한 토큰이 아닙니다." ),
    NOT_EXISTS_AUTHORIZATION(HttpStatus.UNAUTHORIZED, "A-003", "Authorization Header가 빈값입니다."),
    NOT_VALID_BEARER_GRANT_TYPE(HttpStatus.UNAUTHORIZED, "A-004", "인증 타입이 Bearer 타입이 아닙니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "A-005", "해당 refresh token은 존재하지 않습니다."),
    REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "A-006", "해당 refresh token은 만료됐습니다."),
    NOT_ACCESS_TOKEN_TYPE(HttpStatus.UNAUTHORIZED, "A-007", "해당 토큰은 ACCESS TOKEN이 아닙니다."),
    FORBIDDEN_ADMIN(HttpStatus.FORBIDDEN, "A-008", "관리자 Role이 아닙니다."),
    INVALID_PASSWORD(HttpStatus.FORBIDDEN, "A-009", "패스워드가 올바르지 않습니다."),


    // 회원
    INVALID_MEMBER_TYPE(HttpStatus.BAD_REQUEST,"M-001" ,"잘못된 회원 타입입니다. (memberType : KAKAO)" ),
    ALREADY_REGISTERED_MEMBER(HttpStatus.BAD_REQUEST, "M-002", "이미 가입된 회원 입니다."),
    MEMBER_NOT_EXISTS(HttpStatus.BAD_REQUEST, "M-003" , "해당 회원은 존재하지 않습니다."),
    UNAUTHORIZED_MEMBER(HttpStatus.UNAUTHORIZED, "M-004" , "해당 회원은 접근 권한이 없습니다."),



    UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Q-001", "문의 게시글 업데이트에 실패했습니다."),
    QUESTION_NOT_EXISTS(HttpStatus.BAD_REQUEST, "Q-002", "해당 게시글은 존재하지 않습니다."),
    NOT_PUBLIC(HttpStatus.BAD_REQUEST,"Q-003" , "비밀 게시글입니다."),


    ANSWER_NOT_EXISTS(HttpStatus.BAD_REQUEST, "A-001", "해당 게시물의 답변은 존재하지 않습니다."),
    ANSWER_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "A-002", "해당 게시물의 답변이 이미 존재합니다.");


    ErrorCode(HttpStatus httpStatus, String errorCode, String message){
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }

    private HttpStatus httpStatus;
    private String errorCode;
    private String message;
}
