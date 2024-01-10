package com.justsayit.member.service.auth;

import com.justsayit.member.service.command.LoginCommand;
import com.justsayit.member.service.dto.LoginRes;

public interface MemberLoginUseCase {

    LoginRes login(LoginCommand cmd);
}
