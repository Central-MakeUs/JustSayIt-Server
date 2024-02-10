package com.justsayit.member.service.auth.command;

import com.justsayit.member.service.auth.dto.OAuthProvider;

public interface OAuthLoginCommand {

    OAuthProvider oAuthProvider();
    String token();
}
