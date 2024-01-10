package com.justsayit.member.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginRes {

    private Long memberId;
    private String accessToken;

    @Builder
    public LoginRes(Long memberId, String accessToken) {
        this.memberId = memberId;
        this.accessToken = accessToken;
    }
}
