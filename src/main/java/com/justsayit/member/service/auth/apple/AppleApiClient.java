package com.justsayit.member.service.auth.apple;

import com.justsayit.member.service.auth.dto.OAuthInfoResponse;
import com.justsayit.member.service.auth.dto.OAuthProvider;
import com.justsayit.member.service.auth.usecase.OAuthApiClient;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AppleApiClient implements OAuthApiClient {

    @Value("${oauth.apple.url.api}")
    private String GET_PUBLIC_KEY_URL;
    private final String SUB = "sub";
    private final String EMAIL = "email";
    private final AppleJwtUtils appleJwtUtils;
    private final RestTemplate restTemplate;

    @Override
    public OAuthInfoResponse requestOauthInfo(String identityToken) {
        ApplePublicKeyResponse response = getApplePublicKey();
        Claims claims = appleJwtUtils.verifyIdentityTokenAndGetPayload(identityToken, response);
        String sub = String.valueOf(claims.get(SUB));
        String email = String.valueOf(claims.get(EMAIL));
        return new AppleInfoResponse(sub, email);
    }

    public ApplePublicKeyResponse getApplePublicKey() {
        ResponseEntity<ApplePublicKeyResponse> response = restTemplate.getForEntity(GET_PUBLIC_KEY_URL, ApplePublicKeyResponse.class);
        return response.getBody();
    }

    @Override
    public OAuthProvider oAuthProvider() {
        return OAuthProvider.APPLE;
    }
}
