package com.justsayit.member.service.auth.naver;

import com.justsayit.member.service.auth.dto.OAuthInfoResponse;
import com.justsayit.member.service.auth.dto.OAuthProvider;
import com.justsayit.member.service.auth.usecase.OAuthApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Component
@RequiredArgsConstructor
public class NaverApiClient implements OAuthApiClient {

    @Value("${oauth.naver.url.api}")
    private String API_URL;
    private final String BEARER = "Bearer ";
    private final RestTemplate restTemplate;

    @Override
    public OAuthInfoResponse requestOauthInfo(String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(token);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);
        /* 헤더 토큰을 전달하기 위해 get 대신 post 메서드 사용 */
        ResponseEntity<NaverInfoResponse> exchange = restTemplate.exchange(API_URL, HttpMethod.GET, request, NaverInfoResponse.class);
        return exchange.getBody();
    }

    @Override
    public OAuthProvider oAuthProvider() {
        return OAuthProvider.NAVER;
    }
}
