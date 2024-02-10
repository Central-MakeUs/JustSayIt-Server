package com.justsayit.member.service.auth.naver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.justsayit.member.domain.Provider;
import com.justsayit.member.service.auth.dto.OAuthInfoResponse;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverInfoResponse implements OAuthInfoResponse {

    private Response response;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Response {

        private String email;
    }

    @Override
    public String getEmail() {
        return response.getEmail();
    }

    @Override
    public Provider getProvider() {
        return Provider.NAVER;
    }
}
