package com.justsayit.member.service.auth.usecase;

import com.justsayit.member.service.auth.command.LoginCommand;
import com.justsayit.member.service.auth.dto.LoginRes;

public interface LoginUseCase {

    LoginRes login(LoginCommand cmd);
}
