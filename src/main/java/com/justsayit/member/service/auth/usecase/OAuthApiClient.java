package com.justsayit.member.service.auth.usecase;

import com.justsayit.member.service.auth.dto.OAuthInfoResponse;
import com.justsayit.member.service.auth.dto.OAuthProvider;

public interface OAuthApiClient {

    OAuthInfoResponse requestOauthInfo(String token);

    OAuthProvider oAuthProvider();
}
