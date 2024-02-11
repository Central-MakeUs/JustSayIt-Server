package com.justsayit.member.service.auth.apple;

import com.justsayit.member.domain.Provider;
import com.justsayit.member.service.auth.dto.OAuthInfoResponse;

public class AppleInfoResponse implements OAuthInfoResponse {

    private String sub;
    private String email;

    public AppleInfoResponse(String sub, String email) {
        this.sub = sub;
        this.email = email;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public Provider getProvider() {
        return Provider.APPLE;
    }
}
