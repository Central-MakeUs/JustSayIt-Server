package com.justsayit.member.service.auth.apple;

import com.justsayit.member.service.auth.command.OAuthLoginCommand;
import com.justsayit.member.service.auth.dto.OAuthProvider;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AppleLoginCommand implements OAuthLoginCommand {

    private String token;

    @Override
    public OAuthProvider oAuthProvider() {
        return OAuthProvider.APPLE;
    }

    @Override
    public String token() {
        return token;
    }
}
