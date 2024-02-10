package com.justsayit.member.service.auth.dto;

import com.justsayit.member.domain.Provider;

public interface OAuthInfoResponse {

    String getEmail();
    Provider getProvider();
}
