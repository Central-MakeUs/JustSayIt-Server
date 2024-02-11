package com.justsayit.member.service.auth;

import com.justsayit.member.service.auth.command.OAuthLoginCommand;
import com.justsayit.member.service.auth.dto.OAuthInfoResponse;
import com.justsayit.member.service.auth.dto.OAuthProvider;
import com.justsayit.member.service.auth.usecase.OAuthApiClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RequestOAuthInfoService {

    private final Map<OAuthProvider, OAuthApiClient> clients;

    public RequestOAuthInfoService(List<OAuthApiClient> clients) {
        this.clients = clients.stream()
                .collect(
                        Collectors.toUnmodifiableMap(OAuthApiClient::oAuthProvider, Function.identity())
                );
    }

    /* provider로 필요한 ApiClient 구현체를 조회한 후 사용자 정보 조회 요청을 전송 */
    public OAuthInfoResponse request(OAuthLoginCommand cmd) {
        OAuthApiClient client = clients.get(cmd.oAuthProvider());
        return client.requestOauthInfo(cmd.token());
    }
}
