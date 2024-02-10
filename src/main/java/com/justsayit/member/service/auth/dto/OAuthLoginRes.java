package com.justsayit.member.service.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class OAuthLoginRes {

    private String email;
    private String accessToken;
    @JsonProperty(value = "isJoined")
    private boolean joined;

    private OAuthLoginRes(String email, String accessToken, boolean joined) {
        this.email = email;
        this.accessToken = accessToken;
        this.joined = joined;
    }

    public static OAuthLoginRes isJoined(String email, String accessToken) {
        return new OAuthLoginRes(email, accessToken, true);
    }

    public static OAuthLoginRes isNotJoined(String email) {
        return new OAuthLoginRes(email, null, false);
    }
}