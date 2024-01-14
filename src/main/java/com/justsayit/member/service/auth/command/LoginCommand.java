package com.justsayit.member.service.auth.command;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class LoginCommand {

    private String token;
    private String loginType;
    private String nickname;
    private String profileImg;
    private String gender;
    private LocalDate birth;

    @Builder
    public LoginCommand(String token, String loginType, String nickname, String profileImg, String gender, LocalDate birth) {
        this.token = token;
        this.loginType = loginType;
        this.nickname = nickname;
        this.profileImg = profileImg;
        this.gender = gender;
        this.birth = birth;
    }
}
