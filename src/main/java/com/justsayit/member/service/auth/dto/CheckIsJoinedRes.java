package com.justsayit.member.service.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CheckIsJoinedRes {

    @JsonProperty(value = "isJoined")
    private boolean joined;
    private String accessToken;

    private CheckIsJoinedRes(boolean joined, String accessToken) {
        this.joined = joined;
        this.accessToken = accessToken;
    }

    public static CheckIsJoinedRes isJoined(String accessToken) {
        return new CheckIsJoinedRes(true, accessToken);
    }

    public static CheckIsJoinedRes isNotJoined() {
        return new CheckIsJoinedRes(false, null);
    }
}
