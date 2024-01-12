package com.justsayit.member.service.auth.command;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginCommand {

    private String token;
    private String loginType;
    private String nickname;
    private String profileImg;

    @Builder
    public LoginCommand(String token, String loginType, String nickname, String profileImg) {
        this.token = token;
        this.loginType = loginType;
        this.nickname = nickname;
        this.profileImg = profileImg;
    }
}
