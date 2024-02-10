package com.justsayit.member.service.auth;

import com.justsayit.core.jwt.JwtTokenProvider;
import com.justsayit.core.jwt.dto.JwtToken;
import com.justsayit.member.domain.Member;
import com.justsayit.member.domain.Provider;
import com.justsayit.member.repository.MemberRepository;
import com.justsayit.member.service.auth.command.OAuthLoginCommand;
import com.justsayit.member.service.auth.dto.OAuthInfoResponse;
import com.justsayit.member.service.auth.dto.OAuthLoginRes;
import com.justsayit.member.service.auth.usecase.OAuthUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
@Transactional
public class OAuthService implements OAuthUseCase {

    private final RequestOAuthInfoService requestOAuthInfoService;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    @Override
    public OAuthLoginRes naverLogin(OAuthLoginCommand cmd) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(cmd);
        String email = oAuthInfoResponse.getEmail();
        Provider provider = oAuthInfoResponse.getProvider();
        Member member = memberRepository.findByEmailAndProvider(email, provider);
        if (isNull(member)) {
            return OAuthLoginRes.isNotJoined(email);
        }
        JwtToken jwtToken = jwtTokenProvider.createToken(member.getId());
        return OAuthLoginRes.isJoined(email, jwtToken.getAccessToken());
    }
}