package com.justsayit.member.service.auth.usecase;

import com.justsayit.member.service.auth.command.CheckIsJoinedCmd;
import com.justsayit.member.service.auth.command.LoginCommand;
import com.justsayit.member.service.auth.dto.CheckIsJoinedRes;
import com.justsayit.member.service.auth.dto.LoginRes;

public interface AuthUseCase {

    LoginRes login(LoginCommand cmd);

    void quit(Long memberId);

    CheckIsJoinedRes checkIsJoined(CheckIsJoinedCmd checkIsJoinedCmd);
}
