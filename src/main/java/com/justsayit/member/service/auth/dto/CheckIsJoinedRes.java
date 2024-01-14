package com.justsayit.member.service.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CheckIsJoinedRes {

    private Long memberId;
    @JsonProperty(value = "isJoined")
    private boolean joined;
    private String accessToken;

    private CheckIsJoinedRes(Long memberId, boolean joined, String accessToken) {
        this.memberId = memberId;
        this.joined = joined;
        this.accessToken = accessToken;
    }

    public static CheckIsJoinedRes isJoined(Long memberId, String accessToken) {
        return new CheckIsJoinedRes(memberId, true, accessToken);
    }

    public static CheckIsJoinedRes isNotJoined() {
        return new CheckIsJoinedRes(null, false, null);
    }
}
