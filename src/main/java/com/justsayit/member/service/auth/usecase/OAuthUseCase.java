package com.justsayit.member.service.auth.usecase;

import com.justsayit.member.service.auth.command.OAuthLoginCommand;
import com.justsayit.member.service.auth.dto.OAuthLoginRes;

public interface OAuthUseCase {

    OAuthLoginRes naverLogin(OAuthLoginCommand cmd);

    OAuthLoginRes appleLogin(OAuthLoginCommand cmd);
}
