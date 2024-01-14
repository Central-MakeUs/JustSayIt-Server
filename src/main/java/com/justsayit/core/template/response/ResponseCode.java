package com.justsayit.core.template.response;

import lombok.Getter;

@Getter
public enum ResponseCode {

    // 2000 - 성공
    OK("2000", "성공"),

    // 1000 - SECURITY
    UNAUTHENTICATED("1000", "인증되지 않은 사용자입니다."),
    JWT_TOKEN_EXPIRED("1001", "jwt 토큰이 파기되었습니다."),
    NO_ACCESS_PERMISSION("1002", "접근 권한이 없습니다."),

    // 3000 - MEMBER
    NO_MEMBER("3000", "회원이 존재하지 않습니다."),
    ALREADY_EXISTS_MEMBER("3001", "이미 가입한 회원입니다."),
    INVALID_NICKNAME_LENGTH("3002", "닉네임은 2글자 이상, 12글자 이하여야 합니다."),
    ;

    private String code;
    private String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
