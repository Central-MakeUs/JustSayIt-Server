package com.justsayit.member.service.auth.command;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginCommand {

    private String token;
    private String profileImg;
    private String nickname;
    private String loginType;

    @Builder
    public LoginCommand(String token, String profileImg, String nickname, String loginType) {
        this.token = token;
        this.profileImg = profileImg;
        this.nickname = nickname;
        this.loginType = loginType;
    }
}
