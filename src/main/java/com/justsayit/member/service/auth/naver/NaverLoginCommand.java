package com.justsayit.member.service.auth.naver;

import com.justsayit.member.service.auth.command.OAuthLoginCommand;
import com.justsayit.member.service.auth.dto.OAuthProvider;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NaverLoginCommand implements OAuthLoginCommand {

    private String token;

    @Override
    public OAuthProvider oAuthProvider() {
        return OAuthProvider.NAVER;
    }

    @Override
    public String token() {
        return token;
    }
}
