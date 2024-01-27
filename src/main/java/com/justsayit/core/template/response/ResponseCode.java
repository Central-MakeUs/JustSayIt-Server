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

    // 4000 - S3
    FILE_SIZE_OVERFLOW("4000", "개별 사진 사이즈는 최대 10MB, 총합 사이즈는 최대 100MB를 초과할 수 없습니다."),
    FAIL_TO_UPLOAD_FILE("4001", "AWS 서비스가 원활하지 않아 사진 업로드에 실패했습니다."),

    // 5000 - story
    INVALID_BODY_TEXT("5000", "본문 내용은 0자 이상 300자 이하까지 작성 가능하며 공백으로만 작성할 수 없습니다."),
    INVALID_EMOTION_CODE("5001", "잘못된 감정코드입니다."),
    INVALID_NUMBER_OF_IMG("5002", "스토리에 업로드 가능한 사진은 최대 4장입니다."),
    ;
    private String code;
    private String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
