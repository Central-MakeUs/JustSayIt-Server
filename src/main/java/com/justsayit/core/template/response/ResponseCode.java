package com.justsayit.core.template.response;

import lombok.Getter;

@Getter
public enum ResponseCode {

    // 2000 - 성공
    OK("2000", "성공"),

    // 3000 - MEMBER
    NO_MEMBER("3000", "회원이 존재하지 않습니다."),
    ALREADY_EXISTS_MEMBER("3001", "이미 가입한 회원입니다."),
    ;

    private String code;
    private String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
