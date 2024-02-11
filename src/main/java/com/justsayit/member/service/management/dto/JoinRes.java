package com.justsayit.member.service.management.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class JoinRes {

    private Long memberId;
    private String accessToken;

    @Builder
    public JoinRes(Long memberId, String accessToken) {
        this.memberId = memberId;
        this.accessToken = accessToken;
    }
}
